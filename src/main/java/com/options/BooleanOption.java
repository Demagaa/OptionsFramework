package com.options;

import java.util.HashMap;
import java.util.Map;

final class BooleanOption {

    private BooleanOption() {
    }

    private static final Map<String, Boolean> boolMap = initBoolMap();

    static Boolean valueOf(String arg) {
        Boolean res = boolMap.get(arg);
        if (res == null) {
            throw new IllegalArgumentException("Entered value for Boolean is not in dictionary");
        }
        return res;

    }

    private static Map<String, Boolean> initBoolMap() {
        Map<String, Boolean> booleanMap = new HashMap<>();

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

