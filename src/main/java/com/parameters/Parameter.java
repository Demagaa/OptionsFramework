package com.parameters;

import lombok.Setter;

@Setter
public abstract class Parameter {
    public abstract Class<?> getType();

    public abstract Object getDef();
}
