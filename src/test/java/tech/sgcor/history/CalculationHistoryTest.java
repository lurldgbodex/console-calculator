package tech.sgcor.history;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CalculationHistoryTest {
    private CalculationHistory underTest;

    @BeforeEach
    void setup() {
        underTest = new CalculationHistory();
    }

    @Test
    void testCalculationHistory() {
        underTest.addToHistory("3 + 5", 8);
        underTest.addToHistory("2 * 2", 4);

        String expected1 = "3 + 5 = 8";
        String expected2 = "2 * 2 = 4";

        assertThat(underTest.getHistory()).hasSize(2);
        assertThat(underTest.getHistory()).contains(expected1, expected2);
    }

    @Test
    void testCalculationHistory_clearHistory() {
        underTest.addToHistory("3 + 5", 8);

        assertThat(underTest.getHistory()).hasSize(1);

        underTest.clearHistory();
        assertThat(underTest.getHistory()).hasSize(0);
    }
}