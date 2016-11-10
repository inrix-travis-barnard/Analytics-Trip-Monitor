package com.inrix.analytics.trip.metrics.monitor.resources.v1.check.data.input.Provider.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Represents the provider driving profile
 *
 * @author Copyright © 2015 Inrix All Rights Reserved.
 */
public enum ProviderDrivingProfileType {

    Unknown(0, "Unknown"),
    ConsumerVehicle(1, "Consumer Vehicle"),
    Taxi(2, "Taxi / Shuttle / Town Car Service Fleets"),
    FieldService(3, "Field Service / Local Delivery Fleets"),
    ForHire(4, "For Hire / Private Trucking Fleets")
    ;
    private static Map<Integer, ProviderDrivingProfileType> map = null;

    static {
        int size = ProviderDrivingProfileType.values().length;
        map = new HashMap<>(size);

        for (ProviderDrivingProfileType providerType : ProviderDrivingProfileType.values()) {
            map.put(providerType.value, providerType);
        }
    }

    private final Integer value;
    private final String description;

    private ProviderDrivingProfileType(Integer value, String description)
    {
        this.value = value;
        this.description = description;
    }

    public static ProviderDrivingProfileType getType(int value) {
        return map.get(value);
    }

    public static int size() {
        return map.size();
    }

    public Integer getValue() {
        return this.value;
    }

    public Set<Integer> getAllowableValues() {
        return map.keySet();
    }

    public static ProviderDrivingProfileType getTypeFromDescription(String description) {
        for (ProviderDrivingProfileType type : ProviderDrivingProfileType.values()) {
            if (type.getDescription().equals(description)) {
                return type;
            }
        }
        return null;
    }

    public String getDescription() {
        return this.description;
    }
}
