package tech.sgcor.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.sgcor.exceptions.QuitException;
import tech.sgcor.input.InputData;
import tech.sgcor.input.InputHandler;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalculatorTest {
    @Mock
    private ArithmeticOperation arithmeticOperation;
    @Mock
    private InputHandler inputHandler;
    @InjectMocks
    private Calculator calculator;

    @BeforeEach
    void setup() {
        calculator = new Calculator(inputHandler, arithmeticOperation);
    }

    @Test
    void testCalculate_QuitCalculation() {
        InputData firstInput = new InputData(8 , "/", 2);

        when(arithmeticOperation.divide(8, 2)).thenReturn(4.0);

        when(inputHandler.getUserInput()).thenReturn(firstInput);
        doThrow(new QuitException("quit")).when(inputHandler).getNextOperator(4.0);

        calculator.start();

        verify(inputHandler, times(1)).getUserInput();
        verify(inputHandler, times(1)).getNextOperator(4.0);
        verify(arithmeticOperation, times(1)).divide(8, 2);
    }

    @Test
    void testCalculate_ContinuousCalculation() {
        when(arithmeticOperation.add(3, 5)).thenReturn(8.0);
        when(arithmeticOperation.multiply(8.0, 2)).thenReturn(16.0);
        when(arithmeticOperation.subtract(16, 6)).thenReturn(10.0);
        when(arithmeticOperation.log(10)).thenReturn(1.0);
        when(arithmeticOperation.power(1, 5)).thenReturn(5.0);
        when(arithmeticOperation.sqrt(5)).thenReturn(18.0);
        when(arithmeticOperation.sin(18)).thenReturn(12.0);
        when(arithmeticOperation.cos(12)).thenReturn(40.0);
        when(arithmeticOperation.tan(40)).thenReturn(2.0);

        InputData firstInput = new InputData(3, "+", 5);
        InputData nextInput = new InputData(8, "*", 2);
        InputData thirdInput = new InputData(16, "-", 6);
        InputData fourthInput = new InputData(10, "log", 0);
        InputData fifthInput = new InputData(1, "^", 5);
        InputData sixthInput = new InputData(5, "sqrt", 0);
        InputData seventhInput = new InputData(18, "sin", 0);
        InputData eightInput = new InputData(12, "cos", 0);
        InputData ninthInput = new InputData(40, "tan", 0);

        when(inputHandler.getUserInput()).thenReturn(firstInput);
        when(inputHandler.getNextOperator(8.0)).thenReturn(nextInput);
        when(inputHandler.getNextOperator(16.0)).thenReturn(thirdInput);
        when(inputHandler.getNextOperator(10.0)).thenReturn(fourthInput);
        when(inputHandler.getNextOperator(1.0)).thenReturn(fifthInput);
        when(inputHandler.getNextOperator(5.0)).thenReturn(sixthInput);
        when(inputHandler.getNextOperator(18.0)).thenReturn(seventhInput);
        when(inputHandler.getNextOperator(12.0)).thenReturn(eightInput);
        when(inputHandler.getNextOperator(40.0)).thenReturn(ninthInput);
        doThrow(QuitException.class).when(inputHandler).getNextOperator(2.0);

        calculator.start();

        verify(inputHandler, times(1)).getUserInput();
        verify(inputHandler, times(1)).getNextOperator(8.0);
        verify(arithmeticOperation, times(1)).add(3, 5);
        verify(arithmeticOperation, times(1)).multiply(8.0, 2);
    }

    @Test
    void testCalculator_invalidArgument() {
        InputData inputData = new InputData(5, "invalid", 5);
        when(inputHandler.getUserInput()).thenReturn(inputData);

        assertThatThrownBy(calculator::start)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("invalid operator: " + inputData.operator());
    }
}