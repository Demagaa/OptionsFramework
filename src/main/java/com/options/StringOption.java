package com.options;

import lombok.Getter;

final class StringOption {

    @Getter
    private static int maxValue = Integer.MAX_VALUE;
    @Getter
    private static int minValue = Integer.MIN_VALUE;

    private StringOption() {
    }

    static void setMaxValue(int maxValue) {
        StringOption.maxValue = maxValue;
    }

    static void setMinValue(int minValue) {
        if (minValue < 0) {
            throw new IllegalArgumentException("Entered minimal length for String is below zero");
        }
        StringOption.minValue = minValue;
    }


    static String valueOf(String arg) {
        int i = arg.length();
        if (i > maxValue || i < minValue) {
            throw new IllegalArgumentException("Entered value for String is out of specified min/max bounds");
        }
        return arg;
    }
}
