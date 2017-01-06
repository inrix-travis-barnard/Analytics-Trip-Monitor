package com.inrix.analytics.trip.metrics.monitor.resources.v1.check.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yishuai.Li on 10/25/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckResponse {
    public static enum Status {
        OK, WARN, ERROR
    }

    @JsonProperty("type")
    private static final String type = "TripMonitorCheckResponse";

    @JsonProperty("startDate")
    private String startDate;

    @JsonProperty("status")
    private Status status;

    @JsonProperty("overThreshold")
    private List<ProviderCheckInfo> overThreshold;

    @JsonProperty("belowThreshold")
    private List<ProviderCheckInfo> belowThreshold;

    @JsonProperty("missing")
    private List<ProviderCheckInfo> missing;

    public CheckResponse (String startDate, Status status) {
        this.startDate = startDate;
        this.status = status;
        this.overThreshold = new ArrayList<>();
        this.belowThreshold = new ArrayList<>();
        this.missing = new ArrayList<>();
    }

    public void addOverThreshold (List<ProviderCheckInfo> providerCheckInfos){
        this.overThreshold.addAll(providerCheckInfos);
    }

    public void addBelowThreshold (List<ProviderCheckInfo> providerCheckInfos){
        this.belowThreshold.addAll(providerCheckInfos);
    }

    public void addMissing (List<ProviderCheckInfo> providerCheckInfos){
        this.missing.addAll(providerCheckInfos);
    }
}
