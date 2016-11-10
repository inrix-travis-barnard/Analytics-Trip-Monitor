package com.inrix.analytics.trip.metrics.monitor.resources.v1.check.data.input.Provider.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Represents the provider type
 *
 * @author Copyright © 2015 Inrix All Rights Reserved.
 */
public enum ProviderType {

    Unknown(0, "Unknown"), Consumer(1, "consumer"), Fleet(2, "fleet"), Mobile(3, "mobile");
    private static Map<Integer, ProviderType> map = null;

    static {
        int size = ProviderType.values().length;
        map = new HashMap<>(size);

        for (ProviderType providerType : ProviderType.values()) {
            map.put(providerType.value, providerType);
        }
    }

    private final Integer value;
    private final String description;

    private ProviderType(Integer value, String description)
    {
        this.value = value;
        this.description = description;
    }

    public static ProviderType getType(Integer value) {
        return map.get(value);
    }

    public static ProviderType getTypeFromDescription(String description) {
        for (ProviderType type : ProviderType.values()) {
            if (type.getDescription().equals(description)) {
                return type;
            }
        }
        return null;
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

    public String getDescription() {
        return this.description;
    }
}
