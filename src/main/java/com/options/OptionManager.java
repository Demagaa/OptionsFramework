package com.options;

import com.parameters.Integer;
import com.parameters.Parameter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class OptionManager {
    private static final Map<String, Option> optionMap = new HashMap<>();

    private OptionManager() {
    }

    public static Option createOption(boolean argRequired, String desc, String[] alias, Class<?> type, Object def) {
        Option option = new Option(argRequired, desc, alias, type, def);
        for (String argument : alias) {
            optionMap.put(argument, option);
        }
        return option;
    }

    public static Option getOption(String code) {
        return optionMap.get(code);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getParameter(String arg, Option option) {
        Class<?> type = option.getType();

        if (type == com.parameters.String.class) {
            return (T) com.parameters.String.valueOf(arg);
        } else if (type == Integer.class) {
            return (T) Integer.valueOf(arg);
        } else if (type == Boolean.class) {
            return (T) com.parameters.bool.Boolean.valueOf(arg);
        } else if (type.isEnum()) {
            for (Object constant : type.getEnumConstants()) {
                if (Objects.equals(constant.toString().toLowerCase(), arg.toLowerCase())) {
                    return (T) constant;
                }
            }
            throw new IllegalArgumentException("Specified parameter is not an " + type);
        } else {
            try {
                return (T) type.getDeclaredConstructor(String.class).newInstance(arg);
            } catch (Exception e) {
                throw new IllegalArgumentException("wrong parameter type, it should be of type " + type);
            }
        }
    }
}
