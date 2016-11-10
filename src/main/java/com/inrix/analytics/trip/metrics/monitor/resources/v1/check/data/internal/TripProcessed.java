package com.inrix.analytics.trip.metrics.monitor.resources.v1.check.data.internal;

/**
 * Created by Yishuai.Li on 10/25/2016.
 */
public class TripProcessed {
    public Integer getRawProviderId() {
        return rawProviderId;
    }

    public long getTotalTripCount() {
        return totalTripCount;
    }

    private final Integer rawProviderId;
    private final long totalTripCount;

    public TripProcessed (int rawProviderId, long totalTripCount){
        this.rawProviderId = rawProviderId;
        this.totalTripCount = totalTripCount;
    }



}
