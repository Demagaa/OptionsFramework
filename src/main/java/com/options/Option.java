package com.options;

import com.parameters.Integer;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Option {
    // TODO option does not contain value of option //
    private boolean argRequired;
    private String desc;
    private String[] alias;
    Class<?> type;
    Object def;

    public Option(boolean argRequired, String desc, String[] alias, Class<?> type, Object def) {
        if (!type.isInstance(def)){
            throw new IllegalArgumentException("default value" + def + "is not of the type of parameter " + type);
        }
        if (type.isPrimitive()) {
            type = wrapPrimitiveParameter(type);
        }
        this.argRequired = argRequired;
        this.desc = desc;
        this.alias = alias;
        this.type = type;
        this.def = def;
    }

    private static Class<?> wrapPrimitiveParameter(Class<?> type) {
        if (type == String.class) {
            type = com.parameters.String.class;
        } else if (type == Integer.class) {
            type = com.parameters.Integer.class;
        } else if (type == Boolean.class) {
            type = com.parameters.bool.Boolean.class;
        }
        return type;
    }
}
