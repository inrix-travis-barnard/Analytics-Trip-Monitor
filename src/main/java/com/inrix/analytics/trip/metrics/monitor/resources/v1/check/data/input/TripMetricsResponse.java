package com.inrix.analytics.trip.metrics.monitor.resources.v1.check.data.input;

/**
 * Created by Yishuai.Li on 10/28/2016.
 */
import java.util.List;

public class TripMetricsResponse {

    private String type = "TripMetricsResponse";
    private String responseId;

    private List<TripMetrics> tripMetrics;

    public TripMetricsResponse() {
    }

    public TripMetricsResponse(
            String requestId,
            final List<TripMetrics> tripMetrics) {

        this.responseId = requestId;
        this.tripMetrics = tripMetrics;
    }

    public TripMetricsResponse(
            String type,
            String requestId,
            final List<TripMetrics> tripMetrics) {

        this.type = type;
        this.responseId = requestId;
        this.tripMetrics = tripMetrics;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getResponseId() {
        return this.responseId;
    }

    public List<TripMetrics> getTripMetrics() {
        return tripMetrics;
    }

}

