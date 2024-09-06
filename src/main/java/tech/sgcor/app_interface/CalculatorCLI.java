package tech.sgcor.app_interface;

import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import tech.sgcor.calculator.Calculator;
import tech.sgcor.input.InputData;
import tech.sgcor.input.InputHandler;

import java.io.IOException;

public class CalculatorCLI {
    private final Calculator calculator;
    private final InputHandler inputHandler;
    private final LineReader reader;

    public CalculatorCLI(Calculator calculator, InputHandler inputHandler) throws IOException {
        this.calculator = calculator;
        this.inputHandler = inputHandler;
        Terminal terminal = TerminalBuilder.builder()
                .system(true)
                .build();
        this.reader = LineReaderBuilder.builder()
                .terminal(terminal)
                .build();
    }

    public void startShell() {
        while (true) {
            try {
                String input = reader.readLine("cal> ");

                if (input.equalsIgnoreCase("quit") || input.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting...");
                    break;
                }

                if (input.equalsIgnoreCase("history")) {
                    calculator.displayHistory();
                } else if (input.equalsIgnoreCase("help")) {
                    inputHandler.displayHelp();
                } else if (input.equalsIgnoreCase("clear history")) {
                    calculator.clearHistory();
                } else {
                    try {
                        InputData inputData= inputHandler.parseInput(input);
                        Number result = calculator.calculate(inputData);
                        System.out.println("Result: " + result);
                    } catch (Exception ex) {
                        System.out.println("Invalid command: " + input);
                        inputHandler.displayHelp();
                    }
                }
            } catch (UserInterruptException | EndOfFileException ex) {
                System.out.println("Exiting...");
                break;
            }
        }
    }
}
