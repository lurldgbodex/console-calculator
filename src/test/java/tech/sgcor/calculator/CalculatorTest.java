package tech.sgcor.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.sgcor.calculator.ArithmeticOperation;
import tech.sgcor.calculator.Calculator;
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

        calculator.Start();

        verify(inputHandler, times(1)).getUserInput();
        verify(inputHandler, times(1)).getNextOperator(4.0);
        verify(arithmeticOperation, times(1)).divide(8, 2);
    }

    @Test
    void testCalculate_ContinuousCalculation() {
        when(arithmeticOperation.add(3, 5)).thenReturn(8.0);
        when(arithmeticOperation.multiply(8.0, 2)).thenReturn(16.0);
        when(arithmeticOperation.subtract(16, 6)).thenReturn(10.0);

        InputData firstInput = new InputData(3, "+", 5);
        InputData nextInput = new InputData(8, "*", 2);
        InputData thirdInput = new InputData(16, "-", 6);

        when(inputHandler.getUserInput()).thenReturn(firstInput);
        when(inputHandler.getNextOperator(8.0)).thenReturn(nextInput);
        when(inputHandler.getNextOperator(16.0)).thenReturn(thirdInput);
        doThrow(QuitException.class).when(inputHandler).getNextOperator(10.0);

        calculator.Start();

        verify(inputHandler, times(1)).getUserInput();
        verify(inputHandler, times(1)).getNextOperator(8.0);
        verify(arithmeticOperation, times(1)).add(3, 5);
        verify(arithmeticOperation, times(1)).multiply(8.0, 2);
    }

    @Test
    void testCalculator_invalidArgument() {
        InputData inputData = new InputData(5, "invalid", 5);
        when(inputHandler.getUserInput()).thenReturn(inputData);

        assertThatThrownBy(calculator::Start)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("invalid operator: " + inputData.operator());
    }
}