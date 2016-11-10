package com.inrix.analytics.trip.metrics.monitor.resources.v1.check.InputDataProcessor;

import com.inrix.analytics.trip.metrics.monitor.resources.v1.check.data.input.TripMetrics;
import com.inrix.analytics.trip.metrics.monitor.resources.v1.check.data.internal.TripProcessed;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yishuai.Li on 10/26/2016.
 */
public class ByProviderCountryProcessor implements ITripMetricsProcessor{

    private Map<Integer, Long> tripCounts;

    public ByProviderCountryProcessor (){
        tripCounts = new HashMap<>();
    }

    @Override
    public synchronized void aggregate(List<TripMetrics> allData) {
        for (TripMetrics tripMetrics : allData){
           if (tripMetrics.getGeographyType().equals("Country")){
               if (!tripCounts.containsKey(tripMetrics.getRawProviderId())){
                   tripCounts.put(tripMetrics.getRawProviderId(), tripMetrics.getTotalTripCount());
               } else {
                   tripCounts.put(
                           tripMetrics.getRawProviderId(),
                           tripCounts.get(tripMetrics.getRawProviderId()) + tripMetrics.getTotalTripCount());
               }
           }
        }

    }

    @Override
    public Map<Integer, TripProcessed> execute() {
        Map<Integer, TripProcessed> retVal = new HashMap<>();
        tripCounts.forEach((k,v) -> retVal.put(k, new TripProcessed(k, v)));
        return retVal;
    }


}
