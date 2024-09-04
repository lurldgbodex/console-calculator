package tech.sgcor.input;

import tech.sgcor.exceptions.QuitException;
import tech.sgcor.history.CalculationHistory;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputHandler {
    private CalculationHistory history;

    public  void setCalculationHistory(CalculationHistory history) {
        this.history = history;
    }

    public InputData getUserInput() {
       String cmd = "Enter the expression (eg. 3 + 5): ";
       String input = promptUser(cmd);
       if (input.equalsIgnoreCase("history")) {
           displayHistory();
           return getUserInput();
       }
       if (input.equalsIgnoreCase("clear history")) {
           history.clearHistory();
           return getUserInput();
       }
       return parseInput(input);
    }

    public InputData getNextOperator(Number prev) {
        String cmd = "Enter the next operator and number (e.g., + 5), or type 'clear' to reset: " + prev + " ";
        String input = promptUser(cmd);

        if (input.equalsIgnoreCase("quit")) {
            throw new QuitException("I want to quit");
        }

        if (input.equalsIgnoreCase("clear")) {
            return getUserInput();
        }

        return parseInput(input, prev);
    }

    private String promptUser(String cmd) {
        try (Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {
            System.out.print(cmd);
            return scanner.nextLine().trim();
        }
    }

    public InputData parseInput(String input) {
        return parseInput(input, null);
    }

    private InputData parseInput(String input, Number prevResult) {
        if (input.matches("[a-zA-Z]+\\([^)]+\\)")) {
            return parseFunctionInput(input);
        }

        String[] parts = input.split("\\s+");
        if (prevResult == null) {
            return validateInput(parts);
        } else {
            return validateNextOperatorInput(parts, prevResult);
        }
    }

    private InputData parseFunctionInput(String input) {
        Pattern pattern = Pattern.compile("([a-zA-Z]+)\\(([^)]+)\\)");
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            String functionName = matcher.group(1);
            String argument = matcher.group(2);

            if (isValidFunction(functionName)) {
                double operand = parseOperand(argument);
                return new InputData(operand, functionName, 0);
            }
        }
        throw new IllegalArgumentException("Invalid function input: " + input);
    }

    private InputData validateInput(String[] input) {
        if (input.length != 3) {
            throw new IllegalArgumentException("invalid input format. " +
                    "please enter in the format: operand1 operator operand2. e.g '1 - 1'");
        }

        double operand1 = parseOperand(input[0]);
        String operator = validateOperator(input[1]);
        double operand2 = parseOperand(input[2]);

        return new InputData(operand1, operator, operand2);
    }

    private String validateOperator(String operator) {
        switch (operator) {
            case "+", "-", "/", "*", "^", "sqrt", "log", "sin", "cos", "tan" -> {
                return operator;
            }
            default -> throw new IllegalArgumentException("invalid operator format. " +
                    "Enter a valid operator(+,-,*,/)");
        }
    }
    private InputData validateNextOperatorInput(String[] input, Number prev) {
        if (input.length != 2) {
            throw new IllegalArgumentException("invalid input format. sample format: '+ 2'");
        }

        double operand1 = prev.doubleValue();
        String operator = validateOperator(input[0]);
        double operand2 = parseOperand(input[1]);

        return new InputData(operand1, operator, operand2);
    }

    private double parseOperand(String operand) {
        try {
           return Double.parseDouble(operand);
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("invalid number format. Enter a valid number");
        }
    }

    private boolean isValidFunction(String operator) {
        return switch (operator) {
            case "log", "sin", "cos", "tan", "sqrt" -> true;
            default -> false;
        };
    }

    private void displayHistory() {
        System.out.println("** Calculation History **");
        for (String entry : history.getHistory()) {
            System.out.println(entry);
        }
    }
}
