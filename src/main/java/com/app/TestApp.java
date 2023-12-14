package com.app;

import com.options.Option;
import com.options.OptionManager;
import com.parameters.Integer;
import com.parameters.Parameter;

public class TestApp {
    public static void main(java.lang.String[] args) {
        args = new java.lang.String[]{"-s","11"};
        Parameter parameter = OptionManager.createParameter(com.parameters.String.class, "default");
        Parameter inter = OptionManager.createParameter(Integer.class, 0);
        OptionManager.createOption(true, "my string option", new String[]{"-s", "--string"}, parameter);
        OptionManager.createOption(true, "my integer option", new java.lang.String[]{"-i", "--integer"}, inter);

        Option option = OptionManager.getOption(args[0]);
        Parameter parameter1 = OptionManager.getParameter(args[1], option);

        System.out.println(parameter1.getClass());
        System.out.println(parameter1.getValue());
    }
}
