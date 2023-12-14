package com.parameters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Parameter {
    Class<?> type;
    Object def;

    public Object getValue(){
        return null;
    }
}
