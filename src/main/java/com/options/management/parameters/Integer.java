package com.options.management.parameters;

import lombok.Getter;

import static java.lang.Integer.parseInt;

@Getter
final class Integer {

    static void setMaxValue(int maxValue) {
        Integer.maxValue = maxValue;
    }

    static void setMinValue(int minValue) {
        Integer.minValue = minValue;
    }

    @Getter
    private static int maxValue = java.lang.Integer.MAX_VALUE;
    @Getter
    private static int minValue = java.lang.Integer.MIN_VALUE;

    private Integer(){}

    static java.lang.Integer valueOf(java.lang.String arg) {
        int i = parseInt(arg, 10);
        if (i > maxValue || i < minValue) {
            throw new IllegalArgumentException("Entered value for Integer is out of specified min/max bounds");
        }
        return i;
    }
}
