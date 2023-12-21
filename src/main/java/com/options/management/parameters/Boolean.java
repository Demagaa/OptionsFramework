package com.options.management.parameters;

import java.util.HashMap;
import java.util.Map;

final class Boolean {

    private Boolean() {
    }

    private static final Map<java.lang.String, java.lang.Boolean> boolMap = initBoolMap();

    static java.lang.Boolean valueOf(java.lang.String arg) {
        java.lang.Boolean res = boolMap.get(arg);
        if (res == null) {
            throw new IllegalArgumentException("Entered value for Boolean is not in dictionary");
        }
        return res;

    }

    private static Map<java.lang.String, java.lang.Boolean> initBoolMap() {
        Map<java.lang.String, java.lang.Boolean> booleanMap = new HashMap<>();

        booleanMap.put("yes", true);
        booleanMap.put("true", true);
        booleanMap.put("1", true);
        booleanMap.put("on", true);
        booleanMap.put("no", false);
        booleanMap.put("0", false);
        booleanMap.put("off", false);
        booleanMap.put("false", false);
        return booleanMap;
    }
}

