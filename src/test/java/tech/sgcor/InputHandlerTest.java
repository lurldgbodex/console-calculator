package tech.sgcor;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class InputHandlerTest {
    private final InputHandler inputHandler = new InputHandler();
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
        String input = "2 + 1";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        InputData result = inputHandler.getUserInput();

        assertThat(result.operand1()).isEqualTo(2);
        assertThat(result.operator()).isEqualTo("+");
        assertThat(result.operand2()).isEqualTo(1);
    }

    @Test
    void testGetUserInput_failure() {
        String input = "two * 0";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertThatThrownBy(inputHandler::getUserInput)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("invalid number format. Enter a valid number");
    }
}