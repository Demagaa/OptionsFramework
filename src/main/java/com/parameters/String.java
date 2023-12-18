package com.parameters;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class String extends Parameter{
        private static int maxValue = java.lang.Integer.MAX_VALUE;

        public static void setMinValue(int minValue) {
            if (minValue < 0){
                throw new IllegalArgumentException("Entered minimal length for String is below zero");
            }
            String.minValue = minValue;
        }

        private static int minValue = java.lang.Integer.MIN_VALUE;

        public String(Class<?> type, Object def) {
            super(type, def);
        }


        public static java.lang.String valueOf(java.lang.String arg) {
            int i = arg.length();
            if (i > maxValue || i < minValue) {
            throw new IllegalArgumentException("Entered value for String is out of specified min/max bounds");
        }
        return arg;
    }
}
