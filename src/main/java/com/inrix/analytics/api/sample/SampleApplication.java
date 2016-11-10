package com.inrix.analytics.api.sample;

import com.inrix.analytics.api.CoreApplication;

public class SampleApplication extends CoreApplication<SampleConfiguration> {

    SampleApplication() {
        super(SampleConfiguration.class, SampleApplication.class.getPackage().getName(), null);
    }

    public static void main(
            String[] args) throws Exception {

        SampleApplication application = new SampleApplication();
        application.run(args);
    }
}
