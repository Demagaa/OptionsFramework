package com.parameters;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Integer extends Parameter{
    private java.lang.Integer value;
    public Integer(Class<?> type, Object def) {
        super(type, def);
    }

    public Integer(Class<?> type, Object def, java.lang.Integer value) {
        this(type, def);
        this.value = value;
    }

    @Override
    public java.lang.Integer getValue() {
        return value;
    }
}
