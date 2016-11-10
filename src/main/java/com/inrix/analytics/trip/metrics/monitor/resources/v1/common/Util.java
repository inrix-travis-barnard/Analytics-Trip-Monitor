package com.inrix.analytics.trip.metrics.monitor.resources.v1.common;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Yishuai.Li on 11/1/2016.
 */
public class Util {
    public static String stackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }
}
