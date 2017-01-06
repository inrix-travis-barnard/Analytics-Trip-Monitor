package com.inrix.analytics.trip.metrics.monitor.resources.v1.check.checkers;

import com.inrix.analytics.dal.monitorthreshold.model.ThresholdInfo;
import com.inrix.analytics.trip.metrics.monitor.resources.v1.check.data.ProviderCheckInfo;

import java.util.List;

/**
 * Created by Yishuai.Li on 12/21/2016.
 */
public class Checkers {

    public static void percentageCheck(
            List<ProviderCheckInfo> overList,
            List<ProviderCheckInfo> belowList,
            int providerId,
            String providerName,
            long current,
            long previous,
            ThresholdInfo thresholdInfo) {
        double percentDiff = (double)(current - previous) / previous ;
        if (percentDiff > 0) {
            if (percentDiff > thresholdInfo.getUpThresholdPercent()){
                overList.add(new ProviderCheckInfo(
                        providerId,
                        providerName,
                        current,
                        previous,
                        ProviderCheckInfo.ThresholdType.PERCENT,
                        Math.round(thresholdInfo.getUpThresholdPercent() * 1000.0)/10.0,
                        Math.round(percentDiff * 1000.0)/10.0,
                        ProviderCheckInfo.FailureReason.UPTHRESHOLD
                ));
            }
        }else if (percentDiff < 0) {
            if (percentDiff < thresholdInfo.getDownThresholdPercent()){
                belowList.add(new ProviderCheckInfo(
                        providerId,
                        providerName,
                        current,
                        previous,
                        ProviderCheckInfo.ThresholdType.PERCENT,
                        Math.round(thresholdInfo.getDownThresholdPercent() * 1000.0)/10.0,
                        Math.round(percentDiff * 1000.0)/10.0,
                        ProviderCheckInfo.FailureReason.DOWNTHRESHOLD
                ));
            }
        }

    }
}
