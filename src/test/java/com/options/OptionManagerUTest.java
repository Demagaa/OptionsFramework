package com.options;

import com.argument.ArgumentPair;
import com.options.management.parameters.Option;
import com.options.management.parameters.OptionManager;
import com.options.management.parameters.MyCustomObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class OptionManagerUTest {
    @Test
    public void testAddListOption() {
        Option option = OptionManager.getInstance().createOption(true,
                "my list option",
                new String[]{"-l", "--list"},
                List.class,
                new ArrayList<>());

        assert (option.getType().equals(List.class));
    }

    @Test
    public void testAddStringOption() {
        Option option = OptionManager.getInstance().createOption(true,
                "my string option",
                new String[]{"-s", "--string"},
                String.class,
                "default");

        assert (option.getType().equals(String.class));
    }

    @Test
    public void testAddIntegerOption() {
        Option option = OptionManager.getInstance().createOption(true,
                "my int option",
                new String[]{"-i", "--integer"},
                Integer.class, 0);

        assert (option.getType().equals(Integer.class));
    }

    @Test
    public void testAddBooleanOption() {
        Option option = OptionManager.getInstance().createOption(true,
                "my bool option",
                new String[]{"-b", "--bool"},
                Boolean.class, true);

        assert (option.getType().equals(Boolean.class));
        assert (option.getDef() == Boolean.valueOf(true));
    }

    @Test
    public void testAddBooleanOptionWithoutParameter() {
        Option option = OptionManager.getInstance().createOption(false,
                "my bool option",
                new String[]{"-b", "--bool"},
                Boolean.class, true);

        assert (option.getType().equals(Boolean.class));
        assert (option.getDef() == Boolean.valueOf(true));
    }


    @Test
    public void testReadInputString() {
        String input = "-s Hello world";

        Option option = OptionManager.getInstance().createOption(true,
                "my string option",
                new String[]{"-s", "--string"},
                String.class, "default");

        //parse argument
        ArgumentPair argumentPair = ArgumentPair.parseArgument(input);
        //define option
        Option extractedOption = OptionManager.getInstance().getOption(argumentPair.getArgument());
        //get actual value
        Object parameter1 = OptionManager.getInstance().interpretOption(argumentPair.getParameter(), extractedOption);

        assertEquals(extractedOption, option);
        assert (parameter1 instanceof String);
    }

    @Test
    public void testBadReadInputList() {
        String input = "-l new ArrayList()";

        Option option = OptionManager.getInstance().createOption(true,
                "my list option",
                new String[]{"-l", "--list"},
                List.class,
                new ArrayList<>());

        //parse argument
        ArgumentPair argumentPair = ArgumentPair.parseArgument(input);
        //define option
        Option extractedOption = OptionManager.getInstance().getOption(argumentPair.getArgument());
        //get actual value

        assertThrows(IllegalArgumentException.class, () -> {
            Object parameter1 = OptionManager.getInstance().interpretOption(argumentPair.getParameter(), extractedOption);
        });
    }

    @Test
    public void testReadCustomObject() {
        String input = "-c myObjectProperty";

        Option option = OptionManager.getInstance().createOption(true,
                "my custom object option",
                new String[]{"-c", "--custom"},
                MyCustomObject.class,
                null);

        //parse argument
        ArgumentPair argumentPair = ArgumentPair.parseArgument(input);
        //define option
        Option extractedOption = OptionManager.getInstance().getOption(argumentPair.getArgument());
        //get actual value
        Object parameter1 = OptionManager.getInstance().interpretOption(argumentPair.getParameter(), extractedOption);

        assert (parameter1 instanceof MyCustomObject);
        assert ((MyCustomObject) parameter1).arg.equals(argumentPair.getParameter());
    }

    @Test
    public void testReadInputInteger() {
        String input = "-i 10";

        Option option = OptionManager.getInstance().createOption(true,
                "my int option",
                new String[]{"-i", "--int"},
                Integer.class, 0);

        //parse argument
        ArgumentPair argumentPair = ArgumentPair.parseArgument(input);
        //define option
        Option extractedOption = OptionManager.getInstance().getOption(argumentPair.getArgument());
        //get actual value
        Object parameter1 = OptionManager.getInstance().interpretOption(argumentPair.getParameter(), extractedOption);

        assertEquals(extractedOption, option);
        assert (parameter1 instanceof Integer);
    }

    @Test
    public void testReadInputBoolean() {
        String input = "-b on";

        Option option = OptionManager.getInstance().createOption(true,
                "my bool option",
                new String[]{"-b", "--bool"},
                Boolean.class, true);

        //parse argument
        ArgumentPair argumentPair = ArgumentPair.parseArgument(input);
        //define option
        Option extractedOption = OptionManager.getInstance().getOption(argumentPair.getArgument());
        //get actual value
        Object parameter1 = OptionManager.getInstance().interpretOption(argumentPair.getParameter(), extractedOption);

        assertEquals(extractedOption, option);
        assert (parameter1 instanceof Boolean);
    }


    @Test
    public void testReadBadInputInteger() {
        String input = "-i false";
        Option option = OptionManager.getInstance().createOption(true,
                "my int option",
                new String[]{"-i", "--int"},
                Integer.class, 0);

        //parse argument
        ArgumentPair argumentPair = ArgumentPair.parseArgument(input);
        //define option
        Option extractedOption = OptionManager.getInstance().getOption(argumentPair.getArgument());
        assertThrows(IllegalArgumentException.class, () -> {
            OptionManager.getInstance().interpretOption(argumentPair.getParameter(), extractedOption);
        });
    }

    @Test
    public void testReadBadTypeInteger() {
        String input = "-i false";

        Option option = OptionManager.getInstance().createOption(true,
                "my int option",
                new String[]{"-i", "--int"},
                Integer.class, 10);
        //parse argument
        ArgumentPair argumentPair = ArgumentPair.parseArgument(input);
        //define option
        Option extractedOption = OptionManager.getInstance().getOption(argumentPair.getArgument());
        assertThrows(IllegalArgumentException.class, () -> {
            //get actual value
            OptionManager.getInstance().interpretOption(argumentPair.getParameter(), extractedOption);
        });
    }

    @Test
    public void test_setMinValue() {
        OptionManager.getInstance().setMinStringLength(10);
        Option option = OptionManager.getInstance().createOption(true,
                "my string option",
                new String[]{"-s", "--string"},
                String.class, "default");

        assertThrows(IllegalArgumentException.class, () -> {
            OptionManager.getInstance().interpretOption("short", option);
        });
    }
}
