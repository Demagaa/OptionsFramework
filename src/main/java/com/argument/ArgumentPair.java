package com.argument;

public class ArgumentPair {
    private String argument;
    private String parameter;

    public ArgumentPair(String argument, String parameter) {
        this.argument = argument;
        this.parameter = parameter;
    }

    public String getArgument() {
        return argument;
    }

    public String getParameter() {
        return parameter;
    }

    public static ArgumentPair parseArgument(String input) {
        if (input == null) throw new IllegalArgumentException("No input specified");
        input = input.trim();
        String[] parts = input.split("\\s+", 2);
        String argument = null;
        if (!parts[0].isEmpty()) {
            argument = parts[0];
        }
        String parameter = (parts.length > 1) ? parts[1] : null;

        return new ArgumentPair(argument, parameter);
    }
}
