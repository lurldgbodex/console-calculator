package tech.sgcor.calculator;

import org.junit.jupiter.api.Test;

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

    @Test
    void testPower() {
        Number result1 = arithmeticOperation.power(5, 2);
        Number result2 = arithmeticOperation.power(-2, 2);
        Number result3 = arithmeticOperation.power(2, -2);
        Number result4 = arithmeticOperation.power(-2, -3);
        Number result5 = arithmeticOperation.power(5, 0);
        Number result6 = arithmeticOperation.power(0, 2);
        Number result7 = arithmeticOperation.power(10, 0);
        Number result8 = arithmeticOperation.power(5.1, 2);

        assertThat(result1).isEqualTo(25);
        assertThat(result2).isEqualTo(4);
        assertThat(result3).isEqualTo(0.25);
        assertThat(result4).isEqualTo(-0.125);
        assertThat(result5).isEqualTo(1);
        assertThat(result6).isEqualTo(0);
        assertThat(result7).isEqualTo(1);
        assertThat(result8).asString().startsWith("26.00999");
    }

    @Test
    void testSqrt() {
        Number result1 = arithmeticOperation.sqrt(25);
        Number result2 = arithmeticOperation.sqrt(10);
        Number result3 = arithmeticOperation.sqrt(8398576);
        Number result4 = arithmeticOperation.sqrt(0);

        assertThat(result1).isEqualTo(5);
        assertThat(result2).asString().startsWith("3.16227");
        assertThat(result3).asString().startsWith("2898.0296");
        assertThat(result4).isEqualTo(0);

        assertThatThrownBy(() -> arithmeticOperation.sqrt(-25))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Cannot calculate square root of a negative number.");

    }

    @Test
    void testLog() {
        Number result1 = arithmeticOperation.log(10);
        Number result2 = arithmeticOperation.log(88);
        Number result3 = arithmeticOperation.log(1);

        assertThat(result1).isEqualTo(1);
        assertThat(result2).asString().startsWith("1.94448");
        assertThat(result3).isEqualTo(0);

        assertThatThrownBy(() -> arithmeticOperation.log(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Logarithm is undefined for non-positive values.");

        assertThatThrownBy(() -> arithmeticOperation.log(-10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Logarithm is undefined for non-positive values.");
    }

    @Test
    void testSin() {
        Number result1 = arithmeticOperation.sin(0);
        Number result2 = arithmeticOperation.sin(-1);
        Number result3 = arithmeticOperation.sin(-25.8);
        Number result4 = arithmeticOperation.sin(5);
        Number result5 = arithmeticOperation.sin(15.2);
        Number result6 = arithmeticOperation.sin(18.333);

        assertThat(result1).isEqualTo(0);
        assertThat(result2).asString().startsWith("-0.841470984");
        assertThat(result3).asString().startsWith("-0.618835022");
        assertThat(result4).asString().startsWith("-0.958924274");
        assertThat(result5).asString().startsWith("0.4863986888");
        assertThat(result6).asString().startsWith("-0.493888359");
    }

    @Test
    void testCos() {
        Number result1 = arithmeticOperation.cos(0);
        Number result2 = arithmeticOperation.cos(-1);
        Number result3 = arithmeticOperation.cos(-25.8);
        Number result4 = arithmeticOperation.cos(5);
        Number result5 = arithmeticOperation.cos(15.2);
        Number result6 = arithmeticOperation.cos(18.333);

        assertThat(result1).isEqualTo(1);
        assertThat(result2).asString().startsWith("0.54030230");
        assertThat(result3).asString().startsWith("0.78552098");
        assertThat(result4).asString().startsWith("0.28366218");
        assertThat(result5).asString().startsWith("-0.8737369");
        assertThat(result6).asString().startsWith("0.86952532");
    }

    @Test
    void testTan() {
        Number result1 = arithmeticOperation.tan(0);
        Number result2 = arithmeticOperation.tan(-1);
        Number result3 = arithmeticOperation.tan(-25.8);
        Number result4 = arithmeticOperation.tan(5);
        Number result5 = arithmeticOperation.tan(15.2);
        Number result6 = arithmeticOperation.tan(18.333);

        assertThat(result1).isEqualTo(0);
        assertThat(result2).asString().startsWith("-1.5574077");
        assertThat(result3).asString().startsWith("-0.7878020");
        assertThat(result4).asString().startsWith("-3.3805150");
        assertThat(result5).asString().startsWith("-0.5566877");
        assertThat(result6).asString().startsWith("-0.5679976");
    }
}