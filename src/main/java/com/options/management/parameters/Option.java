package com.options.management.parameters;


import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
public class Option {
    private static final Map<Class<?>, Class<?>> wrapMap = initWrapMap();

    private boolean argRequired;
    private java.lang.String desc;
    private java.lang.String[] alias;
    private Class<?> type;
    private Object def;

    public Option(boolean argRequired, java.lang.String desc, java.lang.String[] alias, Class<?> type, Object def) {
        if (type != null) {
            if (!type.isInstance(def) && def != null) {
                throw new IllegalArgumentException("default value " + def + " is not of the type of parameter " + type);
            }
            type = wrapPrimitiveParameter(type);
        }
        this.argRequired = argRequired;
        this.desc = desc;
        this.alias = alias;
        this.type = type;
        this.def = def;
    }


    public Class<?> getType(){
        Class<?> res = wrapMap.get(type);
        if (res == null){
            return type;
        } else {
            return res;
        }
    }
    private static Class<?> wrapPrimitiveParameter(Class<?> type) {
        if (wrapMap.containsValue(type)){
            for (Map.Entry<Class<?>, Class<?>> primitive : wrapMap.entrySet()) {
                if (Objects.equals(primitive, type)) {
                    return primitive.getKey();
                }
            }
        }
        return type;
    }

    private static Map<Class<?>, Class<?>> initWrapMap() {
        Map<Class<?>, Class<?>> map = new HashMap<>();
        map.put(com.options.management.parameters.String.class, java.lang.String.class);
        map.put(java.lang.Integer.class, java.lang.Integer.class);
        map.put(java.lang.Boolean.class, java.lang.Boolean.class);
        return map;
    }
}
