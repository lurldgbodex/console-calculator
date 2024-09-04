package tech.sgcor.calculator;

import tech.sgcor.utils.NumberResult;

import java.math.BigDecimal;
import java.math.MathContext;

public class ArithmeticOperation {
   public Number add(double num1, double num2) {
       double result = num1 + num2;
       if (result % 1 == 0) {
           return new NumberResult((int) result).getValue();
       }
       return new NumberResult(result).getValue();
   }
   public Number subtract(double num1, double num2) {
       BigDecimal operand1 = BigDecimal.valueOf(num1);
       BigDecimal operand2 = BigDecimal.valueOf(num2);

       double result = operand1.subtract(operand2).doubleValue();
       if (result % 1 == 0) {
           return new NumberResult((int) result).getValue();
       }
       return new NumberResult(result).getValue();
   }
   public Number divide(double num1, double num2) {
       if (num2 == 0.0) {
           throw new IllegalArgumentException("zero division not allowed");
       }

       BigDecimal operand1 = BigDecimal.valueOf(num1);
       BigDecimal operand2 = BigDecimal.valueOf(num2);

       double result = operand1.divide(operand2, MathContext.DECIMAL128).doubleValue();

       if (result % 1 == 0) {
           return new NumberResult((int) result).getValue();
       }
       return new NumberResult(result).getValue();
   }
   public Number multiply(double num1, double num2) {
       BigDecimal operand1 = BigDecimal.valueOf(num1);
       BigDecimal operand2 = BigDecimal.valueOf(num2);

       double result = operand1.multiply(operand2).doubleValue();
       if (result % 1 == 0) {
           return new NumberResult((int) result).getValue();
       }
       return new NumberResult(result).getValue();
   }
}
