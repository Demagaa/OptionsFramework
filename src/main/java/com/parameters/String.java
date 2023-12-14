package com.parameters;

public class String extends Parameter{

    private java.lang.String value;
    public String(Class<?> type, Object def) {
        super(type, def);
    }

    public String(Class<?> type, Object def, java.lang.String value) {
        this(type, def);
        this.value = value;
    }

    @Override
    public java.lang.String getValue() {
        return value;
    }
}
