package com.inrix.analytics.trip.metrics.monitor.resources.v1.check.data.input.Provider.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Represents the type of source probe
 *
 * @author Copyright © 2015 Inrix All Rights Reserved.
 */
public enum ProbeIdRotationSchedule {
    Unknown(0, "Unknown"),
    PerTrip(1, "Per Trip"),
    Daily(2, "Daily"),
    Monthly(3, "Monthly")
    ;

    private static Map<Integer, ProbeIdRotationSchedule> map = null;

    static {
        int size = ProbeIdRotationSchedule.values().length;
        map = new HashMap<>(size);

        for (ProbeIdRotationSchedule providerType : ProbeIdRotationSchedule.values()) {
            map.put(providerType.value, providerType);
        }
    }

    private final Integer value;
    private final String description;

    private ProbeIdRotationSchedule(Integer value, String description)
    {
        this.value = value;
        this.description = description;
    }

    public static ProbeIdRotationSchedule getType(Integer value) {
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

    public static ProbeIdRotationSchedule getTypeFromDescription(String description) {
        for (ProbeIdRotationSchedule type : ProbeIdRotationSchedule.values()) {
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
