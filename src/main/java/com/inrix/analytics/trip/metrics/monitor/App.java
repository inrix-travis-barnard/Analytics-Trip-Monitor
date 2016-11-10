package com.inrix.analytics.trip.metrics.monitor;

import com.inrix.analytics.api.CoreApplication;

/**
 * Created by Yishuai.Li on 10/24/2016.
 */
public class App extends CoreApplication<AppConfiguration>{
    App() {
        super(AppConfiguration.class, AppConfiguration.class.getPackage().getName(), null);
    }

    public static void main(
            String[] args) throws Exception {

        App application = new App();
        application.run(args);
    }
}
