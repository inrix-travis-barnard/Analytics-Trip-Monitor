package com.inrix.analytics.trip.metrics.monitor.resources.v1.check.data.input;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TripMetrics {

    private DateTime date;
    private Long totalTripCount;
    private Integer rawProviderId;
    private static DateTimeFormatter dtFormatter = ISODateTimeFormat.dateTime();

    private String country;
    private String region;
    private String geographyType;
    private String geographyName;
    private String providerName;

    private String providerType;
    private String providerDrivingProfile;
    private String vehicleWeightClass;

    private Double totalDistKm;
    private Double avgDistKm;

    private Double totalDurationMinutes;
    private Double avgDurationMinutes;
    private Long totalWaypointCount;
    private Long avgWaypointCount;


    private Long avgWaypointPerTripCount;

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }


    public Integer getRawProviderId() {
        return rawProviderId;
    }

    public void setRawProviderId(Integer rawProviderId) {
        this.rawProviderId = rawProviderId;
    }

    public String getProviderType() {
        return providerType;
    }

    public void setProviderType(String providerType) {
        this.providerType = providerType;
    }

    public String getProviderDrivingProfile() {
        return providerDrivingProfile;
    }

    public void setProviderDrivingProfile(String providerDrivingProfile) {
        this.providerDrivingProfile = providerDrivingProfile;
    }

    public String getVehicleWeightClass() {
        return vehicleWeightClass;
    }

    public void setVehicleWeightClass(String vehicleWeightClass) {
        this.vehicleWeightClass = vehicleWeightClass;
    }

    @JsonIgnore
    public DateTime getDate() {
        return date;
    }

    @JsonProperty("date")
    public String getDateAsString() {
        return dtFormatter.print(date);
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public Long getTotalTripCount() {
        return totalTripCount;
    }

    public void setTotalTripCount(Long totalTripCount) {
        this.totalTripCount = totalTripCount;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getGeographyType() {
        return geographyType;
    }

    public void setGeographyType(String geographyType) {
        this.geographyType = geographyType;
    }

    public String getGeographyName() {
        return geographyName;
    }

    public void setGeographyName(String geographyName) {
        this.geographyName = geographyName;
    }

    public Double getAvgDistKm() {
        return avgDistKm;
    }

    public void setAvgDistKm(Double avgDistKm) {
        this.avgDistKm = avgDistKm;
    }


    public Double getTotalDistKm() {
        return totalDistKm;
    }

    public void setTotalDistKm(Double totalDistKm) {
        this.totalDistKm = totalDistKm;
    }


    public Double getAvgDurationMinutes() {
        return avgDurationMinutes;
    }

    public void setAvgDurationMinutes(Double avgDurationMinutes) {
        this.avgDurationMinutes = avgDurationMinutes;
    }


    public Double getTotalDurationMinutes() {
        return totalDurationMinutes;
    }

    public void setTotalDurationMinutes(Double totalDurationMinutes) {
        this.totalDurationMinutes = totalDurationMinutes;
    }

    public Long getAvgWaypointCount() {
        return avgWaypointCount;
    }

    public void setAvgWaypointCount(Long avgWaypointCount) {
        this.avgWaypointCount = avgWaypointCount;
    }

    public Long getAvgWaypointPerTripCount() {
        return avgWaypointPerTripCount;
    }

    public void setAvgWaypointPerTripCount(Long avgWaypointPerTripCount) {
        this.avgWaypointPerTripCount = avgWaypointPerTripCount;
    }

    public Long getTotalWaypointCount() {
        return totalWaypointCount;
    }

    public void setTotalWaypointCount(Long totalWaypointCount) {
        this.totalWaypointCount = totalWaypointCount;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("date=").append(this.getDateAsString()).append(",");
        if (StringUtils.isNotBlank(this.getCountry())) {
            sb.append("country=").append(this.getCountry()).append(",");
        }
        if (StringUtils.isNotBlank(this.getRegion())) {
            sb.append("region=").append(this.getRegion()).append(",");
        }
        if (StringUtils.isNotBlank(this.getGeographyType())) {
            sb.append("geographyType=").append(this.getGeographyType()).append(",");
        }
        if (StringUtils.isNotBlank(this.getGeographyName())) {
            sb.append("geographyName=").append(this.getGeographyName()).append(",");
        }
        sb.append("providerId=").append(this.getRawProviderId()).append(",");
        sb.append("providerName=").append(this.getProviderName()).append(",");
        sb.append("providerDrivingProfile=").append(this.getProviderDrivingProfile()).append(",");
        sb.append("vehicleWeightClass=").append(this.getVehicleWeightClass()).append(",");
        sb.append("totalTripCount=").append(this.getTotalTripCount());
        sb.append("totalDurationMinutes=").append(this.getTotalDurationMinutes());
        sb.append("totalDistKm=").append(this.getTotalDistKm());
        sb.append("totalWaypointCount=").append(this.getTotalWaypointCount());
        sb.append("avgWaypointCount=").append(this.getAvgWaypointCount());
        sb.append("avgWaypointPerTripCount=").append(this.getAvgWaypointPerTripCount());
        return sb.toString();
    }


}