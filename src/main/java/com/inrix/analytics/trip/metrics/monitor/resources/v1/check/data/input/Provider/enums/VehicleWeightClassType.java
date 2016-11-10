package com.inrix.analytics.trip.metrics.monitor.resources.v1.check.data.input.Provider.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Represents the vehicle weight classification
 *
 * @author Copyright © 2015 Inrix All Rights Reserved.
 */
public enum VehicleWeightClassType {
    Unknown(0, "Unknown"),
    LightDutyTruckOrPassenger(1, "Light Duty Truck/Passenger Vehicle: Ranges from 0 to 14000 lb."),
    MediumDutyTruck(2, "Medium Duty Trucks / Vans: ranges from 14001-26000 lb."),
    HeavyDutyTruck(3, "Heavy Duty Trucks: > 26000 lb.");

    private static Map<Integer, VehicleWeightClassType> map = null;

    static {
        int size = VehicleWeightClassType.values().length;
        map = new HashMap<>(size);
        StringBuilder sb = null;

        for (VehicleWeightClassType providerType : VehicleWeightClassType.values()) {
            map.put(providerType.value, providerType);
        }
    }

    private final Integer value;
    private final String description;

    private VehicleWeightClassType(Integer value, String description)
    {
        this.value = value;
        this.description = description;
    }

    public static VehicleWeightClassType getType(Integer value) {
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

    public static VehicleWeightClassType getTypeFromDescription(String description) {
        for (VehicleWeightClassType type : VehicleWeightClassType.values()) {
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
