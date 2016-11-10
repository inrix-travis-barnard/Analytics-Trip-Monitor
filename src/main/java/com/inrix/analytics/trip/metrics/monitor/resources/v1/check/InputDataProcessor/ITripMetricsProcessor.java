package com.inrix.analytics.trip.metrics.monitor.resources.v1.check.InputDataProcessor;

import com.inrix.analytics.trip.metrics.monitor.resources.v1.check.data.input.TripMetrics;
import com.inrix.analytics.trip.metrics.monitor.resources.v1.check.data.internal.TripProcessed;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yishuai.Li on 10/25/2016.
 */
public interface ITripMetricsProcessor {

    public void aggregate(List<TripMetrics> allData);

    public Map<Integer, TripProcessed> execute();

}
