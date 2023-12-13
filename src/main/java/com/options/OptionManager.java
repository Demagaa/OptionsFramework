package com.options;

import com.parameters.Parameter;


public class OptionManager {
    private OptionManager(){}
    public static Option createOption(Parameter parameter, String desc, String[] alias) {

        return new Option(parameter, desc, alias);
    }

    public static Parameter createParametr(Object value, boolean required, Object type, Object def) {
        return new Parameter(required, (Class<Object>) type, value, def);
    }
}
