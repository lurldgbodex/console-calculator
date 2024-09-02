package tech.sgcor;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class NumberResultTest {

    @Test
    void getValueTest() {
        NumberResult number = new NumberResult(10);
        Number result = number.getValue();

        assertThat(result).isEqualTo(10);
    }

    @Test
    void isIntegerTest() {
        NumberResult intNumber = new NumberResult(5);
        NumberResult doubleNumber = new NumberResult(5.2);

        assertThat(intNumber.isInteger()).isTrue();
        assertThat(doubleNumber.isInteger()).isFalse();
    }

    @Test
    void isDoubleTest() {
        NumberResult doubleNumber = new NumberResult(8.833);
        NumberResult intNumber = new NumberResult(0);

        assertThat(doubleNumber.isDouble()).isTrue();
        assertThat(intNumber.isDouble()).isFalse();
    }
}