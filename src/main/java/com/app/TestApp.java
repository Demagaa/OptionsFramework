package com.app;

import com.options.management.parameters.OptionManager;

import java.util.List;

public class TestApp {
    public static void main(String[] args) {
        setup();

        args = new String[]{"-l", "1", "-r", "1", "-o", "MINUS", "-v"};

        Integer left = null;
        Integer right = null;
        OperatorEnum operator = null;
        Boolean verbose = null;


        List<Object> paramsAsObjects = OptionManager.getInstance().getParamsFromInput(args);

        for (Object param : paramsAsObjects) {
            if (param instanceof Integer && left == null) {
                left = (Integer) param;
            } else if (param instanceof Integer && right == null) {
                right = (Integer) param;
            } else if (param instanceof OperatorEnum) {
                operator = (OperatorEnum) param;
            } else if (param instanceof Boolean) {
                verbose = (Boolean) param;
            }
        }

        Integer result = calculate(left, right, operator);
        printResult(result, left, right, operator, verbose);

    }


    private static void printResult(Integer result, Integer left, Integer right, OperatorEnum operator, Boolean verbose) {
        if (result != null) {
            if (verbose != null && verbose) {
                System.out.println(right + " " + operator + " " + left + " = " + result);
            } else {
                System.out.println(result);
            }
        }
    }


    private static Integer calculate(Integer left, Integer right, OperatorEnum operator) {
        if (left == null || right == null || operator == null) {
            return null;
        }

        switch (operator) {
            case PLUS:
                return left + right;
            case MINUS:
                return left - right;
            case MULTIPLY:
                return left * right;
            case DIVIDE:
                if (right != 0) {
                    return left / right;
                } else {
                    System.out.println("Cannot divide by zero.");
                    return null;
                }
            default:
                System.out.println("Unsupported operator: " + operator);
                return null;
        }
    }

    private static void setup() {
        OptionManager.getInstance().createOption(true, "left operand option", new String[]{"-l"}, Integer.class, 0);
        OptionManager.getInstance().createOption(true, "right operand option", new String[]{"-r"}, Integer.class, 0);
        OptionManager.getInstance().createOption(true, "operator option", new String[]{"-o"}, OperatorEnum.class, null);
        OptionManager.getInstance().createOption(false, "verbose", new String[]{"-v"}, Boolean.class, true);
    }
}
