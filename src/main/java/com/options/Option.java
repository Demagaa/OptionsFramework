package com.options;

import com.parameters.Parameter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Option {
    Parameter parameter;
    String desc;
    String[] alias;
}
