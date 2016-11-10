package com.inrix.analytics.trip.metrics.monitor;

import com.inrix.analytics.api.CoreConfiguration;
import com.inrix.analytics.api.S3Configuration;

/**
 * Created by Yishuai.Li on 10/24/2016.
 */
public class AppConfiguration extends CoreConfiguration{

    private S3Configuration s3ConfigurationThresholdDbProps;

    public S3Configuration getS3ConfigurationThresholdDbProps() {
        return s3ConfigurationThresholdDbProps;
    }

    public void setS3ConfigurationThresholdDbProps(S3Configuration s3ConfigurationThresholdDbProps) {
        this.s3ConfigurationThresholdDbProps = s3ConfigurationThresholdDbProps;
    }
}
