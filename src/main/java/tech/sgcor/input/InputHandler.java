package tech.sgcor.input;

import tech.sgcor.exceptions.QuitException;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class InputHandler {
    public InputData getUserInput() {
       String cmd = "Enter the expression (eg. 3 + 5): ";
       String input = scanner(cmd);
       return parseInput(input);
    }

    public InputData parseInput(String input) {
        String[] parts = input.split("\\s+");
        return validateInput(parts);
    }

    private String scanner(String cmd) {
        try (Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {
            System.out.print(cmd);
            return scanner.nextLine().trim();
        }
    }
    private InputData validateInput(String[] input) {
        if (input.length != 3) {
            throw new IllegalArgumentException("invalid input format. " +
                    "please enter in the format: operand1 operator operand2. e.g '1 - 1'");
        }

        try {
            double operand1 = Double.parseDouble(input[0]);

            String operator = input[1];
            validateOperator(operator);

            double operand2 = Double.parseDouble(input[2]);
            return new InputData(operand1, operator, operand2);
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("invalid number format. Enter a valid number");
        }
    }

    private void validateOperator(String operator) {
        switch (operator) {
            case "+", "-", "/", "*" -> {
            }
            default -> throw new IllegalArgumentException("invalid operator format. " +
                    "Enter a valid operator(+,-,*,/)");
        }
    }

    public InputData getNextOperator(Number prev) {
        String cmd = "Enter the next operator and number (e.g., + 5), or type 'clear' to reset: " + prev + " ";
        String input = scanner(cmd);

        if (input.equalsIgnoreCase("yes")) {
           throw new QuitException("I want to quit");
        }

        if (input.equalsIgnoreCase("clear")) {
            return getUserInput();
        }

        String[] part = input.split("\\s+");
        return validateContinuousInput(part, prev);
    }

    private InputData validateContinuousInput(String[] input, Number prev) {
        if (input.length != 2) {
            throw new IllegalArgumentException("invalid input format. sample format: '+ 2'");
        }

        try {
            double operand1 = prev.doubleValue();
            String operator = input[0];
            double operand2 = Double.parseDouble(input[1]);

            validateOperator(operator);
            return new InputData(operand1, operator, operand2);
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("invalid number format. Enter a valid number");
        }
    }
}
