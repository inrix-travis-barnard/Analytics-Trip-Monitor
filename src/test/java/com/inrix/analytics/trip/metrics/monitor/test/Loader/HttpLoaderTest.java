package com.inrix.analytics.trip.metrics.monitor.test.Loader;

import com.inrix.analytics.trip.metrics.monitor.resources.v1.check.InputDataLoader.HttpLoader;
import com.inrix.analytics.trip.metrics.monitor.resources.v1.check.data.input.TripMetrics;
import org.joda.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

/**
 * Created by Yishuai.Li on 10/28/2016.
 */
public class HttpLoaderTest {

    //@Test
    public void testLoad() {
        HttpLoader loader = new HttpLoader();
        try {
            List<TripMetrics> result = loader.loadData(new LocalDate(2016, 10, 1));
            //assertTrue(result.size() == 2240);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
