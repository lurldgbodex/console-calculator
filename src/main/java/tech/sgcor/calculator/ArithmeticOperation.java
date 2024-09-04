package tech.sgcor.calculator;

import tech.sgcor.utils.NumberResult;

import java.math.BigDecimal;
import java.math.MathContext;

public class ArithmeticOperation {
   public Number add(double num1, double num2) {
       double result = num1 + num2;
       return getValue(result);
   }
   public Number subtract(double num1, double num2) {
       BigDecimal operand1 = BigDecimal.valueOf(num1);
       BigDecimal operand2 = BigDecimal.valueOf(num2);

       double result = operand1.subtract(operand2).doubleValue();
       return getValue(result);
   }
   public Number divide(double num1, double num2) {
       if (num2 == 0.0) {
           throw new IllegalArgumentException("zero division not allowed");
       }

       BigDecimal operand1 = BigDecimal.valueOf(num1);
       BigDecimal operand2 = BigDecimal.valueOf(num2);

       double result = operand1.divide(operand2, MathContext.DECIMAL128).doubleValue();
       return getValue(result);
   }
   public Number multiply(double num1, double num2) {
       BigDecimal operand1 = BigDecimal.valueOf(num1);
       BigDecimal operand2 = BigDecimal.valueOf(num2);

       double result = operand1.multiply(operand2).doubleValue();
       return getValue(result);
   }

   public Number power(double base, double exponent) {
      double result = Math.pow(base, exponent);
      return getValue(result);
   }

   public Number sqrt(double num) {
       if (num < 0) {
           throw new IllegalArgumentException("Cannot calculate square root of a negative number.");
       }

       return getValue(Math.sqrt(num));
   }

   public Number log(double num) {
       if (num <= 0) {
           throw new IllegalArgumentException("Logarithm is undefined for non-positive values.");
       }

       return getValue(Math.log10(num));
   }

   public Number sin(double angle) {
       return getValue(Math.sin(angle));
   }

   public Number cos(double angle) {
       return getValue(Math.cos(angle));
   }

   public Number tan(double angle) {
       return getValue(Math.tan(angle));
   }

   private Number getValue(double num) {
       if (num % 1 == 0) {
           return new NumberResult((int) num).getValue();
       }
       return new NumberResult(num).getValue();
   }
}
