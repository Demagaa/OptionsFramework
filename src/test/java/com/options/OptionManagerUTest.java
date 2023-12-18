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

        Option option = OptionManager.createOption(required, desc, alias, null, null);

        assert (option.getDesc().equals(desc));
        assert (option.getType() == null);
    }

    @Test
    public void testAddStringOption(){
        Option option = OptionManager.createOption(true,
                "my string option",
                new String[]{"-s", "--string"},
                String.class,
                "default");

        assert (option.getType().equals(String.class));
    }

    @Test
    public void testAddIntegerOption(){
        Option option = OptionManager.createOption(true,
                "my int option",
                new String[]{"-i", "--integer"},
                Integer.class, 0);

        assert (option.getType().equals(Integer.class));
    }


    @Test
    public void testReadInputString(){
        String input = "-s Hello world";

        Option option = OptionManager.createOption(true,
                "my string option",
                new String[]{"-s", "--string"},
                String.class, "default");

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
