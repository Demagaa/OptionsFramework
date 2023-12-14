package com.options;

import com.app.TestApp;
import com.parameters.Parameter;
import org.junit.After;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class OptionManagerUTest {
    @Test
    public void testAddOption(){
        boolean required = true;
        String desc = "my test option";
        String[] alias = {"-t", "--test", "tst"};

        Option option = OptionManager.createOption(required, desc, alias, null);
        assert (option.getDesc().equals(desc));
        assert (option.getAlias().equals(alias));

        assert (option.getParameter().getType() != null);
        assert (option.getParameter().getDef() == null);
    }

    @Test
    public void testAddStringOption(){
        Parameter parameter = OptionManager.createParameter(String.class, "default");
        Option option = OptionManager.createOption(true, "my string option", new String[]{"-s", "--string"}, parameter);

        assert (option.getParameter().getDef().equals("default"));

    }

    @Test
    @After
    public void testReadInputString(){
        Parameter parameter = OptionManager.createParameter(String.class, "default");
        Option option = OptionManager.createOption(true, "my string option", new String[]{"-s", "--string"}, parameter);

        InputStream sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream("-s Hello world".getBytes());

        System.setIn(in);

        TestApp.main(new String[]{"-s", "Hello world"});


        System.setIn(sysInBackup);
    }

    private String processUserInput(Scanner scanner, PrintWriter output, String answer) {
        String input = scanner.nextLine();

        output.println();
        return input;
    }
}
