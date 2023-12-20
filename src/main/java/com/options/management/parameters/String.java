package com.options.management.parameters;

import lombok.Getter;

final class String {

    @Getter
    private static int maxValue = java.lang.Integer.MAX_VALUE;
    @Getter
    private static int minValue = java.lang.Integer.MIN_VALUE;

    private String() {
    }

    static void setMaxValue(int maxValue) {
        String.maxValue = maxValue;
    }

    static void setMinValue(int minValue) {
        if (minValue < 0) {
            throw new IllegalArgumentException("Entered minimal length for String is below zero");
        }
        String.minValue = minValue;
    }


    static java.lang.String valueOf(java.lang.String arg) {
        int i = arg.length();
        if (i > maxValue || i < minValue) {
            throw new IllegalArgumentException("Entered value for String is out of specified min/max bounds");
        }
        return arg;
    }
}
