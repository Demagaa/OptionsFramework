package com.parameters;

import lombok.Getter;
import lombok.Setter;

import static java.lang.Integer.parseInt;

@Getter
@Setter
public class Integer extends Parameter{
    private static int maxValue = java.lang.Integer.MAX_VALUE;
    private static int minValue = java.lang.Integer.MIN_VALUE;
    public Integer(Class<?> type, Object def) {
        super(type, def);
    }

    public static java.lang.Integer valueOf(java.lang.String arg) {
        int i = parseInt(arg, 10);
        if (i > maxValue || i < minValue) {
            throw new IllegalArgumentException("Entered value for Integer is out of specified min/max bounds");
        }
        return i;
    }
}
