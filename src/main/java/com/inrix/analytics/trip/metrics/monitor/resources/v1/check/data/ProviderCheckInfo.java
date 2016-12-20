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
        THRESHOLD("This provider's trip count exceeds moving average threshold"),
        NEWAPPEAR("This provider id is not in previous tripMetrics report");

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

    @JsonProperty("totalTripCount")
    private long totalTripCount;

    @JsonProperty("previousAvgTripCount")
    private long previousAvgTripCount;

    @JsonProperty("thresholdType")
    private ThresholdType thresholdType;

    @JsonProperty("threshold")
    private double threshold;

    @JsonProperty("reason")
    private FailureReason reason;

    public ProviderCheckInfo(int rawProviderId, FailureReason reason){
        this.rawProviderId = rawProviderId;
        this.reason = reason;
    }

    public ProviderCheckInfo(int rawProviderId, long totalTripCount, long previousAvgTripCount, ThresholdType thresholdType, double threshold){
        this.rawProviderId = rawProviderId;
        this.totalTripCount = totalTripCount;
        this.previousAvgTripCount = previousAvgTripCount;
        this.threshold = threshold;
        this.thresholdType = thresholdType;
        this.reason = FailureReason.THRESHOLD;
    }

}
