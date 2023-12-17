package com.options;

import com.argument.ArgumentPair;
import com.parameters.Parameter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OptionManagerUTest {

    @Test
    public void testAddOptionWithoutParameter(){
        boolean required = true;
        String desc = "my test option";
        String[] alias = {"-t", "--test", "tst"};

        Option option = OptionManager.createOption(required, desc, alias, null);

        assert (option.getDesc().equals(desc));
        assert (option.getParameter() == null);
    }

    @Test
    public void testAddStringOption(){
        Parameter parameter = OptionManager.createParameter(String.class, "default");
        Option option = OptionManager.createOption(true, "my string option", new String[]{"-s", "--string"}, parameter);

        assert (option.getParameter().getDef().equals("default"));
    }

    @Test
    public void testReadInputString(){
        String input = "-s Hello world";

        Parameter parameter = OptionManager.createParameter(String.class, "default");
        Option option = OptionManager.createOption(true,
                "my string option",
                new String[]{"-s", "--string"},
                parameter);

        //parse argument
        ArgumentPair argumentPair = ArgumentPair.parseArgument(input);
        //define option
        Option extractedOption = OptionManager.getOption(argumentPair.getArgument());
        //get actual value
        Object parameter1 = OptionManager.getParameter(argumentPair.getParameter(), extractedOption);

        assertEquals(extractedOption, option);
        assertEquals(parameter1, argumentPair.getParameter());
    }

}
