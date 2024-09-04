package tech.sgcor.calculator;

import tech.sgcor.input.InputData;
import tech.sgcor.input.InputHandler;
import tech.sgcor.exceptions.QuitException;

public class Calculator {
    private final ArithmeticOperation arithmeticOperation;
    private final InputHandler inputHandler;

    public Calculator(InputHandler inputHandler, ArithmeticOperation op) {
        this.inputHandler = inputHandler;
        this.arithmeticOperation = op;
    }

    private Number calculate(InputData input) {
        double num1 = input.operand1();
        double num2 = input.operand2();
        String operator = input.operator();

        return switch (operator) {
            case "+" -> arithmeticOperation.add(num1, num2);
            case "-" -> arithmeticOperation.subtract(num1, num2);
            case "/" -> arithmeticOperation.divide(num1, num2);
            case "*" -> arithmeticOperation.multiply(num1, num2);
            default -> throw new IllegalArgumentException("invalid operator: " + operator);
        };
    }

    public void Start() {
        Number result;
        InputData input = inputHandler.getUserInput();
        result = calculate(input);

        while (true) {
            System.out.println("Result: " + result);
            try {
                InputData nextInput = inputHandler.getNextOperator(result);
                result = calculate(nextInput);
            } catch (QuitException qe) {
                break;
            }
        }
    }
}
