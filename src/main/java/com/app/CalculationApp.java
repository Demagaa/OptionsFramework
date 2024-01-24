package com.app;

import com.options.OptionManager;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CalculationApp {
    public static void main(String[] args) {
        OptionManager manager = new OptionManager();
        setup(manager);

        List<Object> paramsAsObjects = manager.getOptionParamsAsObjectList(args);
        Result result = getCalculationMembers(paramsAsObjects);

        Integer mathResult = calculate(result.left, result.right, result.operator);
        printResult(mathResult, result.left, result.right, result.operator, result.verbose);

    }

    private static Result getCalculationMembers(List<Object> paramsAsObjects) {
        Queue<Object> paramQueue = new LinkedList<>(paramsAsObjects);

        Integer left = pollInteger(paramQueue);
        Integer right = pollInteger(paramQueue);
        OperatorEnum operator = pollOperator(paramQueue);
        Boolean verbose = pollBoolean(paramQueue);

        return new Result(left, right, operator, verbose);
    }

    private static Integer pollInteger(Queue<Object> queue) {
        if (!queue.isEmpty()) {
            Object param = queue.poll();
            if (param instanceof Integer) {
                return (Integer) param;
            }
            throw new IllegalArgumentException("Wrong parameter " + param.getClass() + ", expected Integer.class");
        }
        throw new IllegalArgumentException("Found end of args, missing Integer.class");
    }

    private static OperatorEnum pollOperator(Queue<Object> queue) {
        if (!queue.isEmpty()) {
            Object param = queue.poll();
            if (param instanceof OperatorEnum) {
                return (OperatorEnum) param;
            }
            throw new IllegalArgumentException("Wrong parameter " + param.getClass() + ", expected OperatorEnum.class");
        }
        throw new IllegalArgumentException("Found end of args, missing OperatorEnum.class");
    }

    private static Boolean pollBoolean(Queue<Object> queue) {
        if (!queue.isEmpty()) {
            Object param = queue.poll();
            if (param instanceof Boolean) {
                return (Boolean) param;
            }
            throw new IllegalArgumentException("Wrong parameter " + param.getClass() + ", expected Boolean.class");
        }
        throw new IllegalArgumentException("Found end of args, missing Boolean.class");
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

    private static void setup(OptionManager manager) {
        manager.setMaxIntValue(100);
        manager.setMinIntValue(10);
        manager.createOption(true, "left operand option", new String[]{"-l"}, Integer.class, 0);
        manager.createOption(true, "right operand option", new String[]{"-r"}, Integer.class, 0);
        manager.createOption(true, "operator option", new String[]{"-o"}, OperatorEnum.class, null);
        manager.createOption(false, "verbose", new String[]{"-v"}, Boolean.class, true);
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
