package com.inrix.analytics.trip.metrics.monitor.resources.v1.check;

import com.google.inject.Inject;
import com.inrix.analytics.api.RequestProperties;
import com.inrix.analytics.api.RequestType;
import com.inrix.analytics.dal.datasource.DALConfigurations;
import com.inrix.analytics.dal.interfaces.IDALResponse;
import com.inrix.analytics.dal.monitorthreshold.model.ThresholdInfo;
import com.inrix.analytics.dal.monitorthreshold.request.GetMaxLookBackDaysRequest;
import com.inrix.analytics.dal.monitorthreshold.request.GetMultipleThresholdRequest;
import com.inrix.analytics.logging.ILogger;
import com.inrix.analytics.logging.IRequestContext;
import com.inrix.analytics.logging.LoggerFactory;
import com.inrix.analytics.trip.metrics.monitor.AppConfiguration;
import com.inrix.analytics.trip.metrics.monitor.resources.v1.check.InputDataLoader.HttpLoader;
import com.inrix.analytics.trip.metrics.monitor.resources.v1.check.InputDataLoader.ProviderDetails.ProviderDetailsS3Source;
import com.inrix.analytics.trip.metrics.monitor.resources.v1.check.InputDataProcessor.ByProviderCountryProcessor;
import com.inrix.analytics.trip.metrics.monitor.resources.v1.check.checkers.Checkers;
import com.inrix.analytics.trip.metrics.monitor.resources.v1.check.data.CheckResponse;
import com.google.common.base.Optional;
import com.inrix.analytics.trip.metrics.monitor.resources.v1.check.data.ProviderCheckInfo;
import com.inrix.analytics.trip.metrics.monitor.resources.v1.check.data.input.Provider.ProviderDetail;
import com.inrix.analytics.trip.metrics.monitor.resources.v1.check.data.input.TripMetrics;
import com.inrix.analytics.trip.metrics.monitor.resources.v1.check.data.internal.TripProcessed;
import com.inrix.analytics.trip.metrics.monitor.resources.v1.common.Util;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import javax.ws.rs.*;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Yishuai.Li on 10/25/2016.
 */

@Path("/v1/tripMonitor/check")
//@Consumes(RequestType.APPLICATION_JSON_UTF8)
@Produces(RequestType.APPLICATION_JSON_UTF8)
public class CheckScan {

    private final AppConfiguration appConfig;

    private static final String MODULE_NAME = "TripMonitorCheck";

    @Inject
    public CheckScan(AppConfiguration appConfig) {
        this.appConfig = appConfig;
    }

    @GET
    public CheckResponse getCheck (@QueryParam("startDate") Optional<String> startDate){
        //return new CheckResponse(startDate.or(DateTime.now().toDateTimeISO().toString()), CheckResponse.Status.OK);

        IRequestContext reqCtx = RequestProperties.get().getRequestContext();
        ILogger logger = LoggerFactory.getLogger();

        LocalDate passIn;
        if(startDate == null){
            passIn = DateTime.now().toLocalDate();
        } else {
            passIn = LocalDate.parse(startDate.get());
        }
        try {
            CheckResponse retVal = mainBody(passIn);
            return retVal;
        } catch (Exception e){
            logger.error(reqCtx, Util.stackTrace(e));
            throw new InternalServerErrorException(e);
        }
    }

    public CheckResponse mainBody (LocalDate startDate) throws Exception{

        DALConfigurations.add(appConfig.getS3ConfigurationThresholdDbProps());
        ProviderDetailsS3Source providerSource = new ProviderDetailsS3Source();

        try {
            // 1 Get provider list
            Map<Integer, ProviderDetail> providerMap = providerSource.loadProviderData();


            // 2 Get ThresholdInfo from monitorThresholdDAL, which contains lookBackDays and percent
            List<Integer> providerList = providerMap.keySet().stream().map(Integer::valueOf).collect(Collectors.toList());
            GetMultipleThresholdRequest thresholdRequest = new GetMultipleThresholdRequest(null, providerList);
            IDALResponse<ThresholdInfo> thresholdInfos = thresholdRequest.execute();
            if (thresholdInfos.getException() != null) {
                thresholdInfos.getException().printStackTrace();
                throw new Exception("Error in getting thresholdInfos " + thresholdInfos.getException());
            }
            List<ThresholdInfo> allThreshold = Arrays.asList(thresholdInfos.getObjects());
            Map<Integer, ThresholdInfo> thresholdMap =
                    allThreshold.stream().collect(Collectors.toMap(
                            ThresholdInfo::getProviderId,
                            c -> c
                    ));

            // 2.1 Get max look back days
            GetMaxLookBackDaysRequest maxLookBackDaysRequest = new GetMaxLookBackDaysRequest(null);
            IDALResponse<Integer> maxLookBackDaysResponse = maxLookBackDaysRequest.execute();
            if (maxLookBackDaysResponse.getException() != null) {
                maxLookBackDaysResponse.getException().printStackTrace();
                throw new Exception("Error in getting max look back days " + maxLookBackDaysResponse.getException());
            }
            int maxLookBackDay = maxLookBackDaysResponse.getSingleObject();

            // 3 Get today's trip metrics
            HttpLoader tripMetricsLoader = new HttpLoader();
            List<TripMetrics> newTripMetrics = tripMetricsLoader.loadData(startDate);
            ByProviderCountryProcessor newProcessed = new ByProviderCountryProcessor();
            newProcessed.aggregate(newTripMetrics);
            Map<Integer, TripProcessed> newCount = newProcessed.execute();

            // 4 Calculate look back averages
            Map<Integer, Long> avgTripCount = getAvgTripCount(maxLookBackDay, startDate);

            List<ProviderCheckInfo> missingList = new ArrayList<>();
            List<ProviderCheckInfo> overList = new ArrayList<>();
            List<ProviderCheckInfo> belowList = new ArrayList<>();

            for (int providerId : providerList){

                // Today's metrics has this provider, but not previous metrics, new provider!
                if (!avgTripCount.containsKey(providerId) && newCount.containsKey(providerId)){
                    missingList.add(new ProviderCheckInfo(providerId, providerMap.get(providerId).getName(),ProviderCheckInfo.FailureReason.NEWAPPEAR));
                    continue;
                }

                if (avgTripCount.containsKey(providerId)){
                    // trip count lower than 2000 will be discarded
                    if (avgTripCount.get(providerId) < 2000){
                        continue;
                    }

                    // Previous metrics has this provider, but missing from today's metrics
                    if (!newCount.containsKey(providerId)){
                        missingList.add(new ProviderCheckInfo(providerId, providerMap.get(providerId).getName(), ProviderCheckInfo.FailureReason.MISSING));
                        continue;
                    } else {
                        Checkers.percentageCheck(
                                overList,
                                belowList,
                                providerId,
                                providerMap.get(providerId).getName(),
                                newCount.get(providerId).getTotalTripCount(),
                                avgTripCount.get(providerId),
                                thresholdMap.get(providerId));
                    }
                }
            }

            CheckResponse.Status finalStatus = getStatus(
                    missingList,
                    overList,
                    belowList
            );

            CheckResponse retVal = new CheckResponse(startDate.toString(), finalStatus);
            retVal.addMissing(missingList);
            retVal.addBelowThreshold(belowList);
            retVal.addOverThreshold(overList);
            return retVal;

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }


    }

    public Map<Integer, Long> getAvgTripCount (int lookBackDays, LocalDate startDate) throws IOException {
        Map<Integer, Integer> counter = new HashMap<>();
        Map<Integer, Long> accumulater = new HashMap<>();

        HttpLoader loader = new HttpLoader();
        for (; lookBackDays>0 ; lookBackDays--){
            LocalDate targetDateStart = startDate.plusDays(-7 * lookBackDays);
            try {
                ByProviderCountryProcessor processor = new ByProviderCountryProcessor();
                processor.aggregate(loader.loadData(targetDateStart));
                Map<Integer, TripProcessed> oneDayTripCounts = processor.execute();
                for (Map.Entry<Integer, TripProcessed> entry : oneDayTripCounts.entrySet()){
                    if (!counter.containsKey(entry.getKey())){
                       counter.put(entry.getKey(), 1);
                    } else {
                        counter.put(entry.getKey(), counter.get(entry.getKey()) + 1);
                    }

                    if (!accumulater.containsKey(entry.getKey())){
                        accumulater.put(entry.getKey(), entry.getValue().getTotalTripCount());
                    } else {
                        accumulater.put(entry.getKey(), accumulater.get(entry.getKey()) + entry.getValue().getTotalTripCount());
                    }
                }
            } catch (IOException ex) {
                throw new IOException("Failed to load data for date:" + targetDateStart.toString(),
                        ex);
            }
        }

        Map<Integer, Long> retVal = new HashMap<>();
        for (Map.Entry<Integer, Long> entry : accumulater.entrySet()){
            retVal.put(entry.getKey(),
                    entry.getValue() / counter.get(entry.getKey()));
        }
        return retVal;
    }

    public CheckResponse.Status getStatus(
            List<ProviderCheckInfo> missingList,
            List<ProviderCheckInfo> overList,
            List<ProviderCheckInfo> belowList) {

        CheckResponse.Status result = CheckResponse.Status.OK;

        if (overList.size() > 0) {
            result = CheckResponse.Status.WARN;
        }

        if (missingList.size() > 0 || belowList.size() > 0) {
            result = CheckResponse.Status.ERROR;
        }
        return result;
    }

}
