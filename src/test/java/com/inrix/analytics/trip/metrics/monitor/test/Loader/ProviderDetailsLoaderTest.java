package com.inrix.analytics.trip.metrics.monitor.test.Loader;

import com.inrix.analytics.trip.metrics.monitor.resources.v1.check.InputDataLoader.ProviderDetails.ProviderDetailsS3Source;
import com.inrix.analytics.trip.metrics.monitor.resources.v1.check.data.input.Provider.ProviderDetail;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by Yishuai.Li on 10/31/2016.
 */
public class ProviderDetailsLoaderTest {

    @Test
    public void testProviderDetailsLoad() throws Exception {
        ProviderDetailsS3Source provider = new ProviderDetailsS3Source();

        Map<Integer, ProviderDetail> providerMap = provider.loadProviderData();
        for (ProviderDetail detail : providerMap.values()){
            System.out.println("(" + detail.getId() + ", 3, 0.2),");
        }


        assertTrue(providerMap.keySet().size() == 164);
    }
}
