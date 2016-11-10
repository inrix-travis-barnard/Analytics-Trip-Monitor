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
        OK, WARN, FAIL
    }

    @JsonProperty("type")
    private static final String type = "TripMonitorCheckResponse";

    @JsonProperty("startDate")
    private String startDate;

    @JsonProperty("status")
    private Status status;

    @JsonProperty("details")
    private List<ProviderCheckInfo> details;

    public CheckResponse (String startDate, Status status) {
        this.startDate = startDate;
        this.status = status;
        this.details = new ArrayList<>();
    }

    public void addDetail (ProviderCheckInfo providerCheckInfo){
        this.details.add(providerCheckInfo);
    }

    public void addDetails (List<ProviderCheckInfo> providerCheckInfos){
        this.details.addAll(providerCheckInfos);
    }
}
