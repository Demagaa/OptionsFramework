package com.options;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class OptionManager {

    public OptionManager() {
        optionMap = new HashMap<>();
        initReservedOptions();
    }

    private final Map<String, Option> optionMap;

    private void initReservedOptions() {
        Option help = createOption(false,
                "option for listing all defined options",
                new String[]{"-h", "--help"},
                HelpOption.class,
                new HelpOption(optionMap));

        for (String argument : help.getAlias()) {
            optionMap.put(argument, help);
        }
    }


    public Map<String, Option> getDefinedOptions() {
        return optionMap;
    }

    public void setMaxIntValue(int value) {
        IntegerOption.setMaxValue(value);
    }

    public void setMinIntValue(int value) {
        IntegerOption.setMinValue(value);
    }

    public void setMinStringLength(int value) {
        StringOption.setMinValue(value);
    }

    public void getMinStringLength() {
        StringOption.getMinValue();
    }

    public void getMaxStringLength() {
        StringOption.getMaxValue();
    }

    public int getMinIntValue() {
        return IntegerOption.getMinValue();
    }

    public int getMaxIntValue() {
        return IntegerOption.getMaxValue();
    }


    public void setMaxStringLength(int value) {
        StringOption.setMaxValue(value);
    }

    public Option createOption(boolean argRequired,
                               String desc,
                               String[] alias,
                               Class<?> type,
                               Object def) {
        Option option = new Option(argRequired, desc, alias, type, def);
        putOptionInMap(alias, option);
        return option;
    }

    private void putOptionInMap(String[] alias, Option option) {
        for (String argument : alias) {

            optionMap.put(argument, option);
        }
    }

    public Option getOption(String code) {
        Option option = optionMap.get(code);
        if (option == null) {
            throw new IllegalArgumentException("no option were found for code: " + code);
        }
        return option;
    }


    public List<Object> getOptionParamsAsObjectList(String[] args) {
        List<Object> result = new ArrayList<>();

        int i = 0;
        while (i < args.length) {
            Option option = getOption(args[i]);
            if (defaultOptionUsed(option)) {
                i++;
                continue;
            }

            if (i + 1 < args.length) {
                Object param;
                if (isParamSpecified(args, i)) {
                    param = getParamOrDefault(args[++i], option);
                } else {
                    if (option.isArgRequired()) {
                        throw new IllegalArgumentException("Parameter is required for option: " + args[i]);
                    }
                    param = option.getDef();
                }
                result.add(param);
            } else {
                result.add(option.getDef());
            }
            i++;
        }
        return result;
    }

    private boolean isParamSpecified(String[] args, int i) {
        return i + 1 < args.length && !isOption(args[i + 1]);
    }

    private boolean defaultOptionUsed(Option option) {
        if (option.getDef() instanceof HelpOption) {
            getParamOrDefault("", option);
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public <T> T getParamOrDefault(String arg, Option option) {
        Class<?> type = option.getType();

        if (type == String.class) {
            return (T) StringOption.valueOf(arg);
        } else if (type == Integer.class) {
            return (T) IntegerOption.valueOf(arg);
        } else if (type == Boolean.class) {
            return (T) BooleanOption.valueOf(arg);
        } else if (type.isEnum()) {
            for (Object constant : type.getEnumConstants()) {
                if (Objects.equals(constant.toString().toLowerCase(), arg.toLowerCase())) {
                    return (T) constant;
                }
            }
            throw new IllegalArgumentException("Specified parameter " + arg + " is of invalid type");
        } else {
            try {
                return (T) type.getDeclaredConstructor(String.class).newInstance(arg);
            } catch (Exception e) {
                throw new IllegalArgumentException("Constructor of specified " + type + " should accept java.lang.String.class as parameter. Use decorator or add appropriate constructor");
            }
        }
    }

    public boolean isOption(String arg) {
        return optionMap.containsKey(arg);
    }
}
