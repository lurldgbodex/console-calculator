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

    public void displayHistory() {
        System.out.println("** Calculation History **");
        if (history.size() < 1) {
            System.out.println("No history found.");
            return;
        }

        for (String entry : history) {
            System.out.println(entry);
        }
    }

    public void clearHistory() {
        history.clear();
        System.out.println("history cleared");
    }
}
