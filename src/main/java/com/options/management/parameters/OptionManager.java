package com.options.management.parameters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class OptionManager {
    private static OptionManager manager;

    private OptionManager() {
        initReservedOptions();
    }

    public static OptionManager getInstance() {
        if (manager == null) {
            manager = new OptionManager();
        }
        return manager;
    }

    private final Map<java.lang.String, Option> optionMap = new HashMap<>();

    private void initReservedOptions() {
        optionMap.clear();
        Option help = createOption(false,
                "option for listing all defined options",
                new java.lang.String[]{"-h", "--help"},
                Help.class,
                new Help(optionMap));
        for (java.lang.String argument : help.getAlias()) {
            optionMap.put(argument, help);
        }
    }

    public Map<java.lang.String, Option> getDefinedOptions() {
        return optionMap;
    }

    public void setMaxIntValue(int value) {
        Integer.setMaxValue(value);
    }

    public void setMinIntValue(int value) {
        Integer.setMinValue(value);
    }

    public void setMinStringLength(int value) {
        String.setMinValue(value);
    }

    public void getMinStringLength() {
        String.getMinValue();
    }

    public void getMaxStringLength() {
        String.getMaxValue();
    }

    public int getMinIntValue() {
        return Integer.getMinValue();
    }

    public int getMaxIntValue() {
        return Integer.getMaxValue();
    }


    public void setMaxStringLength(int value) {
        String.setMaxValue(value);
    }

    public Option createOption(boolean argRequired,
                               java.lang.String desc,
                               java.lang.String[] alias,
                               Class<?> type,
                               Object def) {
        Option option = new Option(argRequired, desc, alias, type, def);
        putOptionInMap(alias, option);
        return option;
    }

    private void putOptionInMap(java.lang.String[] alias, Option option) {
        for (java.lang.String argument : alias) {
            optionMap.put(argument, option);
        }
    }

    public Option getOption(java.lang.String code) {
        Option option = optionMap.get(code);
        if (option == null) {
            throw new IllegalArgumentException("no option were found for code: " + code);
        }
        return option;
    }


    public List<Object> getParamsFromInput(java.lang.String[] args) {
        List<Object> result = new ArrayList<>();
        int len = args.length;
        int i = 0;

        while (i < len) {
            Option option = getOption(args[i]);
            if (defaultOptionUsed(option)) {
                i++;
            } else if (i + 1 < len) {
                Object param;
                if (isParamSpecified(args, i)) {
                    param = OptionManager.getInstance().getParamOrDefault(args[++i], option);
                } else {
                    if (option.isArgRequired()) {
                        throw new IllegalArgumentException("Parameter is required for option: " + args[i]);
                    }
                    param = option.getDef();
                }
                result.add(param);
            } else if (i + 1 >= len) {
                result.add(option.getDef());
            }
            i++;
        }

        return result;
    }

    private static boolean isParamSpecified(java.lang.String[] args, int i) {
        return i + 1 < args.length && !OptionManager.getInstance().isOption(args[i + 1]);
    }

    private static boolean defaultOptionUsed(Option option) {
        if (option.getDef() instanceof Help) {
            OptionManager.getInstance().getParamOrDefault("", option);
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public <T> T getParamOrDefault(java.lang.String arg, Option option) {
        Class<?> type = option.getType();

        if (type == java.lang.String.class) {
            return (T) com.options.management.parameters.String.valueOf(arg);
        } else if (type == java.lang.Integer.class) {
            return (T) com.options.management.parameters.Integer.valueOf(arg);
        } else if (type == java.lang.Boolean.class) {
            return (T) com.options.management.parameters.Boolean.valueOf(arg);
        } else if (type.isEnum()) {
            for (Object constant : type.getEnumConstants()) {
                if (Objects.equals(constant.toString().toLowerCase(), arg.toLowerCase())) {
                    return (T) constant;
                }
            }
            throw new IllegalArgumentException("Specified parameter " + arg + " is of invalid type");
        } else {
            try {
                return (T) type.getDeclaredConstructor(java.lang.String.class).newInstance(arg);
            } catch (Exception e) {
                throw new IllegalArgumentException("Constructor of specified " + type + " should accept java.lang.String.class as parameter. Use decorator or add appropriate constructor");
            }
        }
    }

    public boolean isOption(java.lang.String arg) {
        return optionMap.containsKey(arg);
    }
}
