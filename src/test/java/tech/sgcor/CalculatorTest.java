package tech.sgcor;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CalculatorTest {
    private final Calculator calculator = new Calculator();

    @Test
    void addTest() {
        Number result1 = calculator.add(10, 5);
        Number result2 = calculator.add(1.5, 8);
        Number result3 = calculator.add(8, 0);
        Number result4 = calculator.add(0.8, 1);
        Number result5 = calculator.add(1.1, 0.9);
        Number result6 = calculator.add(-100, 50);
        Number result7 = calculator.add(10, -8);

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
        Number result1 = calculator.subtract(0, 2);
        Number result2 = calculator.subtract(1, 0);
        Number result3 = calculator.subtract(5, 2);
        Number result4 = calculator.subtract(2, 5);
        Number result5 = calculator.subtract(1.0, 0.8);
        Number result6 = calculator.subtract(0.8, 1);
        Number result7 = calculator.subtract(10.2, 5);

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
        Number result1 = calculator.divide(6, 2);
        Number result2 = calculator.divide(2, 6);
        Number result3 = calculator.divide(10.6, 2);
        Number result4 = calculator.divide(2, 10.6);
        Number result5 = calculator.divide(2.2, 10.10);
        Number result6 = calculator.divide(0, 5);
        Number result7 = calculator.divide(-6, 2);
        Number result8 = calculator.divide(4, -2);
        Number result9 = calculator.divide(2, 2);

        assertThatThrownBy(() -> calculator.divide(5, 0))
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
        Number result1 = calculator.multiply(1, 2);
        Number result2 = calculator.multiply(2, 1);
        Number result3 = calculator.multiply(3, 3);
        Number result4 = calculator.multiply(5, -2);
        Number result5 = calculator.multiply(-2, 5);
        Number result6 = calculator.multiply(0, 2);
        Number result7 = calculator.multiply(2.2, 5);
        Number result8 = calculator.multiply(5, 2.3);
        Number result9 = calculator.multiply(-3, -6);

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