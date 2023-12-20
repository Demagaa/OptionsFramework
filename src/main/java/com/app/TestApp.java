package com.app;

import com.options.management.parameters.OperatorEnum;
import com.options.management.parameters.Option;
import com.options.management.parameters.OptionManager;

public class TestApp {
    public static void main(String[] args) {
        setup();

        args = new String[]{"-l", "5", "-r", "6", "-o", "PLUS", "-v", "false"};

        Result argumentsAsObjects = parseInput(args);

        Integer result = calculate(argumentsAsObjects.left, argumentsAsObjects.right, argumentsAsObjects.operator);

        printResult(result, argumentsAsObjects.left, argumentsAsObjects.right, argumentsAsObjects.operator, argumentsAsObjects.verbose);

    }

    private static Result parseInput(String[] args) {
        Integer left = null;
        Integer right = null;
        OperatorEnum operator = null;
        Boolean verbose = null;

        for (int i = 0; i < args.length; i++) {
            Option option = null;
            try {
                option = OptionManager.getInstance().getOption(args[i]);
            } catch (IllegalArgumentException e) {
                continue;
            }

            Object param = null;
            try {
                param = OptionManager.getInstance().interpretOption(args[i + 1], option);
            } catch (Exception e) {
                param = option.getDef();
            }


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
        return new Result(left, right, operator, verbose);
    }

    private static class Result {
        public final Integer left;
        public final Integer right;
        public final OperatorEnum operator;
        public final Boolean verbose;

        public Result(Integer left, Integer right, OperatorEnum operator, Boolean verbose) {
            this.left = left;
            this.right = right;
            this.operator = operator;
            this.verbose = verbose;
        }
    }


    private static void printResult(Integer result, Integer left, Integer right, OperatorEnum operator, Boolean verbose) {
        if (verbose) {
            System.out.println(right + " " + operator + " " + left + " = " + result);
        } else {
            System.out.println(result);
        }
    }


    private static Integer calculate(Integer left, Integer right, OperatorEnum operator) {
        if (left == null || right == null || operator == null) {
            System.out.println("Invalid input. Missing operand or operator.");
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
        OptionManager.getInstance().createOption(true, "verbose", new String[]{"-v"}, Boolean.class, true);
    }
}
