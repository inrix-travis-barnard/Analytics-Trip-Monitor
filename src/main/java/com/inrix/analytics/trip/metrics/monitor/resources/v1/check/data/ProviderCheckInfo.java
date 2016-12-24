package com.inrix.analytics.trip.metrics.monitor.resources.v1.check.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by Yishuai.Li on 10/25/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProviderCheckInfo {
    public static enum ThresholdType {
        PERCENT
    }

    public enum FailureReason {
        MISSING("This provider id is missing from today's tripMetrics"),
        UPTHRESHOLD("This provider's trip count exceeds moving average threshold"),
        DOWNTHRESHOLD("This provider's trip count drop below moving average threshold"),
        NEWAPPEAR("This provider id is not in previous tripMetrics report. New provider?");

        private final String msg;

        FailureReason(String msg) {
            this.msg = msg;
        }

        @JsonValue
        String msg() {
            return this.msg;
        }
    }

    @JsonProperty("rawProviderId")
    private int rawProviderId;

    @JsonProperty("providerName")
    private String providerName;

    @JsonProperty("totalTripCount")
    private long totalTripCount;

    @JsonProperty("previousAvgTripCount")
    private long previousAvgTripCount;

    @JsonProperty("thresholdType")
    private ThresholdType thresholdType;

    @JsonProperty("threshold")
    private double threshold;

    @JsonProperty("percentage")
    private double percentage;

    @JsonProperty("reason")
    private FailureReason reason;

    public ProviderCheckInfo(int rawProviderId, String providerName, FailureReason reason){
        this.rawProviderId = rawProviderId;
        this.providerName = providerName;
        this.reason = reason;
    }

    public ProviderCheckInfo(int rawProviderId, String providerName, long totalTripCount, long previousAvgTripCount, ThresholdType thresholdType, double threshold, double percentage, FailureReason reason){
        this.rawProviderId = rawProviderId;
        this.providerName = providerName;
        this.totalTripCount = totalTripCount;
        this.previousAvgTripCount = previousAvgTripCount;
        this.threshold = threshold  ;
        this.percentage = percentage  ;
        this.thresholdType = thresholdType;
        this.reason = reason;
    }

}
