package com.inrix.analytics.trip.metrics.monitor.resources.v1.check.InputDataLoader;

import com.inrix.analytics.trip.metrics.monitor.resources.v1.check.data.input.TripMetrics;

import java.io.IOException;
import java.util.List;

import org.joda.time.LocalDate;

/**
 * Created by Yishuai.Li on 10/27/2016.
 */
public interface ITripMetricsLoader {
    public List<TripMetrics> loadData (LocalDate startDate) throws IOException;
}
