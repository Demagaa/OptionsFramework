package com.options;

import lombok.Getter;

@Getter
final class IntegerOption {

    static void setMaxValue(int maxValue) {
        IntegerOption.maxValue = maxValue;
    }

    static void setMinValue(int minValue) {
        IntegerOption.minValue = minValue;
    }

    @Getter
    private static int maxValue = Integer.MAX_VALUE;
    @Getter
    private static int minValue = Integer.MIN_VALUE;

    private IntegerOption(){}

    static Integer valueOf(String arg) {
        int i = Integer.parseInt(arg, 10);
        if (i > maxValue || i < minValue) {
            throw new IllegalArgumentException("Entered value for Integer is out of specified min/max bounds");
        }
        return i;
    }
}
