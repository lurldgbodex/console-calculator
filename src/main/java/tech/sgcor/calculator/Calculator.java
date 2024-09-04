package tech.sgcor.calculator;

import tech.sgcor.history.CalculationHistory;
import tech.sgcor.input.InputData;
import tech.sgcor.input.InputHandler;
import tech.sgcor.exceptions.QuitException;

public class Calculator {
    private final ArithmeticOperation arithmeticOperation;
    private final CalculationHistory history;
    private final InputHandler inputHandler;

    public Calculator(InputHandler inputHandler, ArithmeticOperation op) {
        this.inputHandler = inputHandler;
        this.arithmeticOperation = op;
        this.history = new CalculationHistory();

        this.inputHandler.setCalculationHistory(history);
    }

    private Number calculate(InputData input) {
        double num1 = input.operand1();
        double num2 = input.operand2();
        String operator = input.operator();
        String expression = num1 + " " + operator + " " + num2;

        return switch (operator) {
            case "+" -> {
                Number result = arithmeticOperation.add(num1, num2);
                history.addToHistory(expression, result);
                yield result;
            }
            case "-" -> {
                Number result = arithmeticOperation.subtract(num1, num2);
                history.addToHistory(expression, result);
                yield result;
            }
            case "/" -> {
                Number result = arithmeticOperation.divide(num1, num2);
                history.addToHistory(expression, result);
                yield result;
            }
            case "*" -> {
                Number result = arithmeticOperation.multiply(num1, num2);
                history.addToHistory(expression, result);
                yield result;
            }
            case "^" -> {
                Number result = arithmeticOperation.power(num1, num2);
                history.addToHistory(expression, result);
                yield result;
            }
            case "sqrt" -> {
                Number result = arithmeticOperation.sqrt(num1);
                expression = operator + "(" + num1 + ")";
                history.addToHistory(expression, result);
                yield result;
            }
            case "log" -> {
                Number result = arithmeticOperation.log(num1);
                expression = operator + "(" + num1 + ")";
                history.addToHistory(expression, result);
                yield result;
            }
            case "sin" -> {
                Number result = arithmeticOperation.sin(num1);
                expression = operator + "(" + num1 + ")";
                history.addToHistory(expression, result);
                yield result;
            }
            case "cos" -> {
                Number result = arithmeticOperation.cos(num1);
                expression = operator + "(" + num1 + ")";
                history.addToHistory(expression, result);
                yield result;
            }
            case "tan" -> {
                Number result = arithmeticOperation.tan(num1);
                expression = operator + "(" + num1 + ")";
                history.addToHistory(expression, result);
                yield result;
            }
            default -> throw new IllegalArgumentException("invalid operator: " + operator);
        };
    }

    public void start() {
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
