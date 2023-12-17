package com.options;

import com.parameters.Parameter;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Option {
    private boolean argRequired;
    private String desc;
    private String[] alias;
    private Parameter parameter;
    public Option(boolean argRequired, String desc, String[] alias, Parameter parameter) {
        this.argRequired = argRequired;
        this.desc = desc;
        this.alias = alias;
        this.parameter = parameter;
    }
}
