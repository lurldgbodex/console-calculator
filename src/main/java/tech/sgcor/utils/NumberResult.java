package tech.sgcor.utils;

public class NumberResult {
    private final Number value;

    public NumberResult(int value) {
        this.value = value;
    }

    public NumberResult(double value) {
        this.value = value;
    }

    public Number getValue() {
        return this.value;
    }

    public boolean isInteger() {
        return value instanceof Integer;
    }

    public boolean isDouble() {
        return value instanceof Double;
    }
}
