package com.parameters.bool;

import com.parameters.Parameter;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class Boolean extends Parameter {

    private static Map<String, java.lang.Boolean> boolMap = initBoolMap();
    private java.lang.Boolean def = false;

    public static java.lang.Boolean valueOf(String arg) {
        java.lang.Boolean res = boolMap.get(arg);
        if (res == null) {
            throw new IllegalArgumentException("Entered value for Boolean is not in dictionary");
        }
        return res;

    }

    private static Map<String, java.lang.Boolean> initBoolMap() {
        Map<String, java.lang.Boolean> booleanMap = new HashMap<>();

        booleanMap.put("yes", true);
        booleanMap.put("1", true);
        booleanMap.put("on", true);
        booleanMap.put("no", false);
        booleanMap.put("0", false);
        booleanMap.put("off", false);
        return booleanMap;
    }

    @Override
    public Class<?> getType() {
        return java.lang.Boolean.class;
    }

    @Override
    public Object getDef() {
        return def;
    }
}

