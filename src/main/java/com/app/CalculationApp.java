package com.app;

import com.options.management.parameters.OptionManager;

import java.util.List;

public class CalculationApp {
    public static void main(String[] args) {
        setup();

        List<Object> paramsAsObjects = OptionManager.getInstance().getParamsFromInput(args);

        Result result = getCalculationMembers(paramsAsObjects);

        Integer mathResult = calculate(result.left, result.right, result.operator);
        printResult(mathResult, result.left, result.right, result.operator, result.verbose);

    }

    private static Result getCalculationMembers(List<Object> paramsAsObjects) {
        Integer left = null;
        Integer right = null;
        OperatorEnum operator = null;
        Boolean verbose = null;

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
        return new Result(left, right, operator, verbose);
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
}
