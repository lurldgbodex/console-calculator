package tech.sgcor.input;

import tech.sgcor.exceptions.QuitException;
import tech.sgcor.history.CalculationHistory;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputHandler {
    private CalculationHistory history;
    private final Scanner scanner;

    public InputHandler(Scanner scanner){
        this.scanner = scanner;
    }

    public  void setCalculationHistory(CalculationHistory history) {
        this.history = history;
    }

    public InputData getUserInput() {
       String cmd = "Enter the expression (eg. 3 + 5): ";
       String input = promptUser(cmd);
       if (input.equalsIgnoreCase("history")) {
           history.displayHistory();
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

        if (isValidFunction(input)) {
            String validInput = input + "(" + prev + ")";
            return parseInput(validInput);
        }

        return parseInput(input, prev);
    }

    private String promptUser(String cmd) {
        System.out.print(cmd);
        return scanner.nextLine().trim();
    }

    public InputData parseInput(String input) {
        return parseInput(input, null);
    }

    public void displayHelp() {
        System.out.println("\n=== HELP MENU ===");
        System.out.println("1. Arithmetic operations: Enter in the format 'operand1 operator operand2'");
        System.out.println(" Supported operators: +, -, *, /, ^");
        System.out.println("2. Function operations: Enter in the format 'function(operand)'");
        System.out.println(" Supported functions: log, sin, cos, tan, sqrt");
        System.out.println("3. View calculation history: Type 'history'.");
        System.out.println("4. Clear calculation history: Type 'clear history'");
        System.out.println("5. Quit the CLI: Type 'quit or exit'.");
        System.out.println("6. Show this help menu: Type 'help'.\n");
    }

    private InputData parseInput(String input, Number prevResult) {
        input = input.trim();

        if (input.matches("[a-zA-Z]+\\([^)]+\\)")) {
            return parseFunctionInput(input);
        }

        input = input.replaceAll("\\s*([+\\-*/^])\\s*", " $1 "); // Add spaces around operators
        input = input.replaceAll("\\s+", " ");

        String[] parts = input.split(" ");
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
        if (input.length != 3) {
            throw new IllegalArgumentException("invalid input format. sample format: '+ 2'");
        }

        double operand1 = prev.doubleValue();
        String operator = validateOperator(input[1]);
        double operand2 = parseOperand(input[2]);

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
}
