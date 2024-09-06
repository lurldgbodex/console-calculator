package tech.sgcor;

import tech.sgcor.app_interface.CalculatorCLI;
import tech.sgcor.calculator.ArithmeticOperation;
import tech.sgcor.calculator.Calculator;
import tech.sgcor.input.InputHandler;

import java.io.IOException;
import java.util.Scanner;

public class CalculatorApp {
    public static void main(String[] args) throws IOException {
        ArithmeticOperation arithmeticOperation = new ArithmeticOperation();
        Scanner scanner = new Scanner(System.in);

        InputHandler inputHandler = new InputHandler(scanner);
        Calculator calculator = new Calculator(inputHandler, arithmeticOperation);

        System.out.println("Choose input method: ");
        System.out.println("1. Command-Line Interface (CLI)");
        System.out.println("2. Basic (Scanner)");
        System.out.print("choice: ");

        int choice = new Scanner(System.in).nextInt();
        if (choice == 1) {
            CalculatorCLI cli = new CalculatorCLI(calculator, inputHandler);
            cli.startShell();
        } else if (choice == 2) {
            calculator.start();
        } else {
            System.out.println("Invalid choice. Exiting.");
        }
    }
}
