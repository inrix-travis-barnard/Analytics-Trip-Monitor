package com.inrix.analytics.trip.metrics.monitor.resources.v1.check.InputDataLoader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inrix.analytics.trip.metrics.monitor.resources.v1.check.data.input.TripMetrics;
import com.inrix.analytics.trip.metrics.monitor.resources.v1.check.data.input.TripMetricsResponse;
import org.joda.time.LocalDate;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;


/**
 * Created by Yishuai.Li on 10/27/2016.
 */
public class HttpLoader implements ITripMetricsLoader {

    private Client client;
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public List<TripMetrics> loadData(LocalDate start) throws IOException {
        client = ClientBuilder.newClient();

        LocalDate end = start.plusDays(1);
        String downloadUrl = "http://analytics-trips-api.inrix.com/analytics-population-api/v1/tripMetrics";

        String parameters = String.format("fromDate=%sT00:00:00.000Z&toDate=%sT00:00:00.000Z&byGeography=true&dateInterval=day" +
                "&token=FYPb3wPvb-69vYDL37uT39OEaqz*CujXLsNeQw5Od0U|",
        start.toString("yyyy-MM-dd"),
                end.toString("yyyy-MM-dd"));

        //parameters = URLEncoder.encode(parameters, "UTF-8");

        Invocation.Builder builder = client.target(downloadUrl)
                .queryParam("fromDate", start.toString("yyyy-MM-dd") + "T00:00:00.000Z")
                .queryParam("toDate", end.toString("yyyy-MM-dd") + "T00:00:00.000Z")
                .queryParam("dateInterval", "day")
                .queryParam("token", "FYPb3wPvb-69vYDL37uT39OEaqz*CujXLsNeQw5Od0U|")
                .queryParam("byGeography", "true")
                .request("application/json");
        Response response = builder.get();
        String json = response.readEntity(String.class);

        TripMetricsResponse tripMetricsResponse = MAPPER.readValue(json, TripMetricsResponse.class);

        return tripMetricsResponse.getTripMetrics();
    }
}
