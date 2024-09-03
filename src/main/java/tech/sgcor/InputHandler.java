package tech.sgcor;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class InputHandler {
    public InputData getUserInput() {
       String input = scanner();
       return parseInput(input);
    }

    public InputData parseInput(String input) {
        String[] parts = input.split("\\s+");
        return validateInput(parts);
    }

    private String scanner() {
        try (Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8)) {
            System.out.println("Enter the expression (eg. 3 + 5): ");
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
}
