package com.options;

import com.argument.ArgumentPair;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArgumentPairTest {
    @Test
    public void test_valid_input() {
        // Arrange
        String input = "argument parameter";

        // Act
        ArgumentPair argumentPair = ArgumentPair.parseArgument(input);

        // Assert
        assertEquals("argument", argumentPair.getArgument());
        assertEquals("parameter", argumentPair.getParameter());
    }

    @Test
    public void test_no_parameter() {
        // Arrange
        String input = "argument";

        // Act
        ArgumentPair argumentPair = ArgumentPair.parseArgument(input);

        // Assert
        assertEquals("argument", argumentPair.getArgument());
        assertNull(argumentPair.getParameter());
    }

    @Test
    public void test_empty_string() {
        // Arrange
        String input = "";

        // Act
        ArgumentPair argumentPair = ArgumentPair.parseArgument(input);

        // Assert
        assertNull(argumentPair.getArgument());
        assertNull(argumentPair.getParameter());
    }

    @Test
    public void test_null_input() {
        // Arrange
        String input = null;

        assertThrows(IllegalArgumentException.class, () -> {
            ArgumentPair argumentPair = ArgumentPair.parseArgument(input);
        });
    }


    @Test
    public void test_spaces_input() {
        // Arrange
        String input = "   argument   ";

        // Act
        ArgumentPair argumentPair = ArgumentPair.parseArgument(input);

        // Assert
        assertEquals("argument", argumentPair.getArgument());
        assertNull(argumentPair.getParameter());
    }
}
