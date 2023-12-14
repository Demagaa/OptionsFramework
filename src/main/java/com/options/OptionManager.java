package com.options;

import com.parameters.Integer;
import com.parameters.Parameter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class OptionManager {
    private static final Map<String, Option> optionMap = new HashMap<>();
    private OptionManager(){}
    public static Option createOption(boolean argRequired, String desc, String[] alias, Parameter parameter) {
        Option option = new Option(argRequired, desc, alias, parameter);
        for (String type : alias) {
            optionMap.put(type, option);
        }
        return option;
    }

    public static Option getOption(String code){
        return optionMap.get(code);
    }

    public static Parameter createParameter(Class<?> type, Object def) {
        return new Parameter(type, def);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getParameter(String arg, Option option) {
        Class<?> type = option.getParameter().getType();

        if (type == com.parameters.String.class) {
            return (T) new com.parameters.String(type, option.getParameter().getDef(), arg);
        } else if (type == Integer.class) {
            return (T) new Integer(type, option.getParameter().getDef(), java.lang.Integer.valueOf(arg));
        } else if (type == Boolean.class) {
            return (T) Boolean.valueOf(arg);
// TODO        } else if (type.isEnum()) {
//            Class<? extends Enum<?>> enumType = (Class<? extends Enum<?>>) type;
//            return (T) Enum.valueOf(enumType, arg);
        } else {
            try {
                return (T) type.getDeclaredConstructor(String.class).newInstance(arg);
            } catch (Exception e) {
                System.out.println("wrong parameter type, it should be of type " + type);
                throw new IllegalArgumentException(e);
            }
        }
    }


}
