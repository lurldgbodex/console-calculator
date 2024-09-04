package tech.sgcor.history;

import java.util.ArrayList;
import java.util.List;

public class CalculationHistory {
    private final List<String> history;

    public CalculationHistory() {
        this.history = new ArrayList<>();
    }

    public void addToHistory(String expression, Number result) {
        String entry = expression + " = " + result;
        history.add(entry);
    }

    public List<String> getHistory() {
        return history;
    }

    public void clearHistory() {
        history.clear();
    }
}
