package tech.sgcor.input;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.sgcor.exceptions.QuitException;

import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InputHandlerTest {
    @Mock
    private Scanner scanner;
    private InputHandler inputHandler;

    @BeforeEach
    void setup() {
        inputHandler = new InputHandler(scanner);
    }

    @AfterEach
    void tearDown() {
        System.setIn(System.in);
    }

    @Test
    void testParseInput_ValidInput() {
        String input = "3.0 + 5.8";
        InputData inputData = inputHandler.parseInput(input);

        assertThat(inputData.operand1()).isEqualTo(3.0);
        assertThat(inputData.operator()).isEqualTo("+");
        assertThat(inputData.operand2()).isEqualTo(5.8);
    }

    @Test
    void testGetUserInput_InvalidInput() {
        String input1 = "three + 33";

        assertThatThrownBy(() -> inputHandler.parseInput(input1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("invalid number format. Enter a valid number");

        // invalid operator
        String input2 = "3 plus 8";
        assertThatThrownBy(()-> inputHandler.parseInput(input2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("invalid operator format. Enter a valid operator(+,-,*,/)");

        // incomplete argument
        String input3 = "5 - ";
        assertThatThrownBy(() -> inputHandler.parseInput(input3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("invalid input format. please enter in the format: operand1 operator operand2. e.g '1 - 1'");

        // incorrect operand3 format
        String input4 = "4 * nine";
        assertThatThrownBy(() -> inputHandler.parseInput(input4))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("invalid number format. Enter a valid number");
    }

    @Test
    void testGetUserInput_success() {
        String input = "2 + 1\n";
        when(scanner.nextLine()).thenReturn(input);

        InputData result = inputHandler.getUserInput();

        assertThat(result.operand1()).isEqualTo(2);
        assertThat(result.operator()).isEqualTo("+");
        assertThat(result.operand2()).isEqualTo(1);
    }

    @Test
    void testGetUserInput_failure() {
        String input = "two * 0";
        when(scanner.nextLine()).thenReturn(input);

        assertThatThrownBy(inputHandler::getUserInput)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("invalid number format. Enter a valid number");
    }

    @Test
    void testGetNextOperation_validInput() {
        String input = "/ 8";
        when(scanner.nextLine()).thenReturn(input);

        InputData result = inputHandler.getNextOperator(16);

        assertThat(result.operand1()).isEqualTo(16);
        assertThat(result.operator()).isEqualTo("/");
        assertThat(result.operand2()).isEqualTo(8);
    }

    @Test
    void testGetNextOperation_invalidInput() {
        String input = "//8";
        when(scanner.nextLine()).thenReturn(input);

        assertThatThrownBy(() -> inputHandler.getNextOperator(8))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("invalid input format. sample format: '+ 2'");
    }

    @Test
    void testGetNextOperation_clearAsInput() {
        InputHandler mockHandler = spy(new InputHandler(scanner));

        InputData inputData = new InputData(3, "+", 5);
        doReturn(inputData).when(mockHandler).getUserInput();

        String input = "clear";
        when(scanner.nextLine()).thenReturn(input);

        InputData result = mockHandler.getNextOperator(10);

        assertThat(result.operand1()).isEqualTo(3);
        assertThat(result.operand2()).isEqualTo(5);
        assertThat(result.operator()).isEqualTo("+");
    }

    @Test
    void testGetNextOperation_quitAsInput() {
        String input = "quit";
        when(scanner.nextLine()).thenReturn(input);

        assertThatThrownBy(() -> inputHandler.getNextOperator(5))
                .isInstanceOf(QuitException.class);
    }

    @Test
    void testGetNextOperation_illegalInput() {
        String input = "* nine";
        when(scanner.nextLine()).thenReturn(input);

        assertThatThrownBy(() -> inputHandler.getNextOperator(8))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("invalid number format. Enter a valid number");
    }

    @Test
    void testAdvancedOperation_sinFunctionInput() {
        String input = "sin(5)";
        when(scanner.nextLine()).thenReturn(input);

        InputData result = inputHandler.getUserInput();

        assertThat(result.operand1()).isEqualTo(5);
        assertThat(result.operator()).isEqualTo("sin");
        assertThat(result.operand2()).isEqualTo(0);
    }

    @Test
    void testAdvancedOperation_cosFunctionInput() {
        String input = "cos(180)";
        when(scanner.nextLine()).thenReturn(input);

        InputData result = inputHandler.getUserInput();

        assertThat(result.operand1()).isEqualTo(180);
        assertThat(result.operator()).isEqualTo("cos");
        assertThat(result.operand2()).isEqualTo(0);
    }

    @Test
    void testAdvancedOperation_tanFunction() {
        String input = "tan(8)";
        when(scanner.nextLine()).thenReturn(input);

        InputData result = inputHandler.getUserInput();

        assertThat(result.operand1()).isEqualTo(8);
        assertThat(result.operator()).isEqualTo("tan");
        assertThat(result.operand2()).isEqualTo(0);
    }

    @Test
    void testAdvancedOperation_sqrtFunction() {
        String input = "sqrt(4)";
        when(scanner.nextLine()).thenReturn(input);

        InputData result = inputHandler.getUserInput();

        assertThat(result.operand1()).isEqualTo(4);
        assertThat(result.operator()).isEqualTo("sqrt");
        assertThat(result.operand2()).isEqualTo(0);
    }

    @Test
    void testAdvancedOperation_logFunction() {
        String input = "log(10)";
        when(scanner.nextLine()).thenReturn(input);

        InputData result = inputHandler.getUserInput();

        assertThat(result.operand1()).isEqualTo(10);
        assertThat(result.operator()).isEqualTo("log");
        assertThat(result.operand2()).isEqualTo(0);
    }

    @Test
    void testAdvancedOperation_powerFunction() {
        String input = "5 ^ 2";
        when(scanner.nextLine()).thenReturn(input);

        InputData result = inputHandler.getUserInput();

        assertThat(result.operand1()).isEqualTo(5);
        assertThat(result.operator()).isEqualTo("^");
        assertThat(result.operand2()).isEqualTo(2);
    }
}