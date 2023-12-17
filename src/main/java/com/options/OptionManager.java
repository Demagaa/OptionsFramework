package com.options;

import com.parameters.Integer;
import com.parameters.MyEnum;
import com.parameters.Parameter;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


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
            return (T) arg;
        } else if (type == Integer.class) {
            return (T) java.lang.Integer.valueOf(arg);
        } else if (type == Boolean.class) {
            return (T) Boolean.valueOf(arg);
        } else if (type.isEnum()) {
            for (Object consta : type.getEnumConstants()) {
                if (Objects.equals(consta.toString().toLowerCase(), arg.toLowerCase())) {
                    //System.out.println("Match");
                    return (T) consta;
                }
            }
            // TODO throw exception, that value is not in enum //
            return null;
            //return (T) arg;
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
