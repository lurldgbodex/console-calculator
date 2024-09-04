package tech.sgcor.calculator;

import org.junit.jupiter.api.Test;
import tech.sgcor.calculator.ArithmeticOperation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArithmeticOperationTest {
    private final ArithmeticOperation arithmeticOperation = new ArithmeticOperation();

    @Test
    void addTest() {
        Number result1 = arithmeticOperation.add(10, 5);
        Number result2 = arithmeticOperation.add(1.5, 8);
        Number result3 = arithmeticOperation.add(8, 0);
        Number result4 = arithmeticOperation.add(0.8, 1);
        Number result5 = arithmeticOperation.add(1.1, 0.9);
        Number result6 = arithmeticOperation.add(-100, 50);
        Number result7 = arithmeticOperation.add(10, -8);

        assertThat(result1).isEqualTo(15);
        assertThat(result2).isEqualTo(9.5);
        assertThat(result3).isEqualTo(8);
        assertThat(result4).isEqualTo(1.8);
        assertThat(result5).isEqualTo(2);
        assertThat(result6).isEqualTo(-50);
        assertThat(result7).isEqualTo(2);
    }

    @Test
    void subtractTest() {
        Number result1 = arithmeticOperation.subtract(0, 2);
        Number result2 = arithmeticOperation.subtract(1, 0);
        Number result3 = arithmeticOperation.subtract(5, 2);
        Number result4 = arithmeticOperation.subtract(2, 5);
        Number result5 = arithmeticOperation.subtract(1.0, 0.8);
        Number result6 = arithmeticOperation.subtract(0.8, 1);
        Number result7 = arithmeticOperation.subtract(10.2, 5);

        assertThat(result1).isEqualTo(-2);
        assertThat(result2).isEqualTo(1);
        assertThat(result3).isEqualTo(3);
        assertThat(result4).isEqualTo(-3);
        assertThat(result5).isEqualTo(0.2);
        assertThat(result6).isEqualTo(-0.2);
        assertThat(result7).isEqualTo(5.2);
    }

    @Test
    void divideTest() {
        Number result1 = arithmeticOperation.divide(6, 2);
        Number result2 = arithmeticOperation.divide(2, 6);
        Number result3 = arithmeticOperation.divide(10.6, 2);
        Number result4 = arithmeticOperation.divide(2, 10.6);
        Number result5 = arithmeticOperation.divide(2.2, 10.10);
        Number result6 = arithmeticOperation.divide(0, 5);
        Number result7 = arithmeticOperation.divide(-6, 2);
        Number result8 = arithmeticOperation.divide(4, -2);
        Number result9 = arithmeticOperation.divide(2, 2);

        assertThatThrownBy(() -> arithmeticOperation.divide(5, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("zero division not allowed");

        assertThat(result1).isEqualTo(3);
        assertThat(result2).asString().startsWith("0.333");
        assertThat(result3).isEqualTo(5.3);
        assertThat(result4).asString().startsWith("0.188679245");
        assertThat(result5).asString().startsWith("0.217821782");
        assertThat(result6).isEqualTo(0);
        assertThat(result7).isEqualTo(-3);
        assertThat(result8).isEqualTo(-2);
        assertThat(result9).isEqualTo(1);
    }

    @Test
    void multiplyTest() {
        Number result1 = arithmeticOperation.multiply(1, 2);
        Number result2 = arithmeticOperation.multiply(2, 1);
        Number result3 = arithmeticOperation.multiply(3, 3);
        Number result4 = arithmeticOperation.multiply(5, -2);
        Number result5 = arithmeticOperation.multiply(-2, 5);
        Number result6 = arithmeticOperation.multiply(0, 2);
        Number result7 = arithmeticOperation.multiply(2.2, 5);
        Number result8 = arithmeticOperation.multiply(5, 2.3);
        Number result9 = arithmeticOperation.multiply(-3, -6);

        assertThat(result1).isEqualTo(2);
        assertThat(result2).isEqualTo(2);
        assertThat(result3).isEqualTo(9);
        assertThat(result4).isEqualTo(-10);
        assertThat(result5).isEqualTo(-10);
        assertThat(result6).isEqualTo(0);
        assertThat(result7).isEqualTo(11);
        assertThat(result8).isEqualTo(11.5);
        assertThat(result9).isEqualTo(18);
    }
}