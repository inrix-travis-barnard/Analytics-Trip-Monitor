package com.inrix.analytics.trip.metrics.monitor.resources.v1.check.data.input.Provider.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Represents the type of source probe
 *
 * @author Copyright © 2015 Inrix All Rights Reserved.
 */
public enum ProbeSourceType {
    Unknown(0, "Unknown"),
    EmbeddedGPS(1, "Embedded GPS"),
    MobileDevice(2, "Mobile Device");

    private static Map<Integer, ProbeSourceType> map = null;

    static {
        int size = ProbeSourceType.values().length;
        map = new HashMap<>(size);

        for (ProbeSourceType providerType : ProbeSourceType.values()) {
            map.put(providerType.value, providerType);
        }
    }

    private final Integer value;
    private final String description;

    private ProbeSourceType(Integer value, String description)
    {
        this.value = value;
        this.description = description;
    }

    public static ProbeSourceType getType(Integer value) {
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

    public static ProbeSourceType getTypeFromDescription(String description) {
        for (ProbeSourceType type : ProbeSourceType.values()) {
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
