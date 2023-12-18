package com.app;

import com.options.Option;
import com.options.OptionManager;
import com.parameters.Integer;
import com.parameters.MyEnum;
import com.parameters.OtherEnum;
import com.parameters.Parameter;

public class TestApp {
    public static void main(java.lang.String[] args) {
        setup();
        args = new java.lang.String[]{"--enum","kek"};


        Option option = OptionManager.getOption(args[0]);
        Object parameter = OptionManager.getParameter(args[1], option);

        assert parameter != null;

        System.out.println(parameter.getClass());
        
        //do something?
    }

    private static void setup() {
        Parameter parameter = OptionManager.createParameter(com.parameters.String.class, "default");
        Parameter inter = OptionManager.createParameter(Integer.class, 0);
        Parameter testNum = OptionManager.createParameter(MyEnum.class, MyEnum.TEST);
        Parameter test = OptionManager.createParameter(OtherEnum.class, OtherEnum.KEK);
        OptionManager.createOption(true, "my string option", new String[]{"-s", "--string"}, parameter);
        OptionManager.createOption(true, "my integer option", new String[]{"-i", "--integer"}, inter);
        OptionManager.createOption(true, "enum test", new String[]{"-e"}, testNum);
        OptionManager.createOption(true, "enum test2", new String[]{"--enum"}, test);
    }
}
