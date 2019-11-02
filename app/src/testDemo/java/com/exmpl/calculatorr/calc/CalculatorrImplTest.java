package com.exmpl.calculatorr.calc;

import com.exmpl.calculatorr.excpt.NaNException;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.exmpl.calculatorr.calc.CalculatorrImpl.operate;
import static com.exmpl.calculatorr.calc.CalculatorrImpl.parse;
import static com.exmpl.calculatorr.calc.CalculatorrImpl.toPostfx;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public final class CalculatorrImplTest {

    @Test
    public void positiveInfinity_parseToDouble_parsed() {
        final String infinity = "Infinity";
        final double rslt = Double.valueOf(infinity);
        assertTrue(Double.isInfinite(rslt) && rslt > 0);
    }

    @Test
    public void negativeInfinity_parseToDouble_parsed() {
        final String infinity = "-Infinity";
        final double rslt = Double.valueOf(infinity);
        assertTrue(Double.isInfinite(rslt) && rslt < 0);
    }

    @Test
    public void unknownOperator_operate_throwsRuntimeException() {
        final String oprtr = "&";
        final String oprnd1 = "123.45";
        final String oprnd2 = "76.55";
        try {
            operate(oprtr, oprnd1, oprnd2);
        } catch (RuntimeException e) {
            return;
        } catch (NaNException e) {
            fail();
        }
        fail();
    }

    @Test
    public void twoPositiveValues_plus_calculated() {
        final String oprtr = "+";
        final String oprnd1 = "123.45";
        final String oprnd2 = "76.55";
        try {
            final String rslt = operate(oprtr, oprnd1, oprnd2);
            assertEquals(200.0, Double.valueOf(rslt));
        } catch (NaNException e) {
            fail();
        }
    }

    @Test
    public void positiveAndNegativeValues_plus_calculated() {
        final String oprtr = "+";
        final String oprnd1 = "123.45";
        final String oprnd2 = "-23.45";
        try {
            final String rslt = operate(oprtr, oprnd1, oprnd2);
            assertEquals(100.0, Double.valueOf(rslt));
        } catch (NaNException e) {
            fail();
        }
    }

    @Test
    public void negativeInfinityAndImmediateValue_plus_negativeInfinity() {
        final String oprtr = "+";
        final String oprnd1 = "-∞";
        final String oprnd2 = "77.55";
        try {
            final String rslt = operate(oprtr, oprnd1, oprnd2);
            assertEquals("-∞", rslt);
        } catch (NaNException e) {
            fail();
        }
    }

    @Test
    public void zeroAndNegativeValue_plus_calculated() {
        final String oprtr = "+";
        final String oprnd1 = "0.0";
        final String oprnd2 = "-23.45";
        try {
            final String rslt = operate(oprtr, oprnd1, oprnd2);
            assertEquals(-23.45, Double.valueOf(rslt));
        } catch (NaNException e) {
            fail();
        }
    }

    @Test
    public void positiveInfinityAndNegativeInfinity_plus_throwsNaNException() {
        final String oprtr = "+";
        final String oprnd1 = "∞";
        final String oprnd2 = "-∞";
        try {
            operate(oprtr, oprnd1, oprnd2);
        } catch (NaNException e) {
            return;
        }
        fail();
    }

    @Test
    public void positiveInfinityAndPositiveInfinity_plus_positiveInfinity() {
        final String oprtr = "+";
        final String oprnd1 = "∞";
        final String oprnd2 = "∞";
        try {
            final String rslt = operate(oprtr, oprnd1, oprnd2);
            assertEquals("∞", rslt);
        } catch (NaNException e) {
            fail();
        }
    }

    @Test
    public void negativeInfinityAndNegativeInfinity_plus_negativeInfinity() {
        final String oprtr = "+";
        final String oprnd1 = "-∞";
        final String oprnd2 = "-∞";
        try {
            final String rslt = operate(oprtr, oprnd1, oprnd2);
            assertEquals("-∞", rslt);
        } catch (NaNException e) {
            fail();
        }
    }

    @Test
    public void negativeInfinityAndImmediateValue_minus_negativeInfinity() {
        final String oprtr = "-";
        final String oprnd1 = "-∞";
        final String oprnd2 = "77.55";
        try {
            final String rslt = operate(oprtr, oprnd1, oprnd2);
            assertEquals("-∞", rslt);
        } catch (NaNException e) {
            fail();
        }
    }

    @Test
    public void positiveAndNegativeValues_minus_calculated() {
        final String oprtr = "-";
        final String oprnd1 = "123.45";
        final String oprnd2 = "23.45";
        try {
            final String rslt = operate(oprtr, oprnd1, oprnd2);
            assertEquals(100.0, Double.valueOf(rslt));
        } catch (NaNException e) {
            fail();
        }
    }

    @Test
    public void negativeInfinityAndNegativeInfinity_minus_throwsNaNException() {
        final String oprtr = "-";
        final String oprnd1 = "-∞";
        final String oprnd2 = "-∞";
        try {
            operate(oprtr, oprnd1, oprnd2);
        } catch (NaNException e) {
            return;
        }
        fail();
    }

    @Test
    public void negativeInfinityAndNegativeInfinity_multiply_positiveInfinity() {
        final String oprtr = "×";
        final String oprnd1 = "-∞";
        final String oprnd2 = "-∞";
        try {
            final String rslt = operate(oprtr, oprnd1, oprnd2);
            assertEquals("∞", rslt);
        } catch (NaNException t) {
            fail();
        }
    }

    @Test
    public void positiveInfinityAndNegativeInfinity_multiply_positiveInfinity() {
        final String oprtr = "×";
        final String oprnd1 = "∞";
        final String oprnd2 = "-∞";
        try {
            final String rslt = operate(oprtr, oprnd1, oprnd2);
            assertEquals("-∞", rslt);
        } catch (NaNException t) {
            fail();
        }
    }

    @Test
    public void zeroAndInfinity_multiply_throwsNaNException() {
        final String oprtr = "×";
        final String oprnd1 = "0";
        final String oprnd2 = "∞";
        try {
            operate(oprtr, oprnd1, oprnd2);
        } catch (NaNException e) {
            return;
        }
        fail();
    }

    @Test
    public void zeroAndZero_divide_throwsNaNException() {
        final String oprtr = "÷";
        final String oprnd1 = "0";
        final String oprnd2 = "0";
        try {
            operate(oprtr, oprnd1, oprnd2);
        } catch (NaNException e) {
            return;
        }
        fail();
    }

    @Test
    public void zeroAndInfinity_divide_zero() {
        final String oprtr = "÷";
        final String oprnd1 = "0";
        final String oprnd2 = "∞";
        try {
            final String rslt = operate(oprtr, oprnd1, oprnd2);
            assertEquals(0.0, Double.valueOf(rslt));
        } catch (NaNException e) {
            fail();
        }
    }

    @Test
    public void exprWithOnlyPositiveValues_parse_parsed() {
        final String expr = "1+3×5÷100";
        final String[] actual = parse(expr);
        final String[] expected = {"1", "+", "3", "×", "5", "÷", "100"};
        assertEquals(Arrays.toString(expected), Arrays.toString(actual));
    }

    @Test
    public void exprWithPositiveAndNegativeValues_parse_parsed() {
        final String expr = "-1+3×-5÷100";
        final String[] actual = parse(expr);
        final String[] expected = {"-1", "+", "3", "×", "-5", "÷", "100"};
        assertEquals(Arrays.toString(expected), Arrays.toString(actual));
    }

    @Test
    public void infixExpr1_toPostfx_parsed() {
        final String[] expr = {"1", "+", "3", "×", "5", "÷", "100"};
        final String[] actual = toPostfx(expr);
        final String[] expctd = {"1", "3", "5", "×", "100", "÷", "+"};
        assertEquals(Arrays.toString(expctd), Arrays.toString(actual));
    }

    @Test
    public void infixExpr2_toPostfx_parsed() {
        final String[] expr = {"-1", "+", "3", "×", "-5", "÷", "100", "-", "56", "×", "9"};
        final String[] actual = toPostfx(expr);
        final String[] expctd = {"-1", "3", "-5", "×", "100", "÷", "+", "56", "9", "×", "-"};
        assertEquals(Arrays.toString(expctd), Arrays.toString(actual));
    }

    @Test
    public void expr1_calculate_calculated() {
        final CalculatorrImpl calculatorr = new CalculatorrImpl();
        final String expr = "-1+3×5-7-56÷8";
        final double expctd = 0.0;
        try {
            final String actual = calculatorr.calculate(expr);
            assertEquals(expctd, Double.valueOf(actual));
        } catch (NaNException e) {
            fail();
        }
    }

    @Test
    public void expr2_calculate_calculated() {
        final CalculatorrImpl calculatorr = new CalculatorrImpl();
        final String expr = "-1+3×5-7-56÷8×200-10000÷25÷8×3-7×90";
        final double expctd = -2173.0;
        try {
            final String actual = calculatorr.calculate(expr);
            assertEquals(expctd, Double.valueOf(actual));
        } catch (NaNException e) {
            fail();
        }
    }

    @Test
    public void exprWithInfinity_calculate_infinity() {
        final CalculatorrImpl calculatorr = new CalculatorrImpl();
        final String expr = "-1+∞";
        final String expctd = "∞";
        try {
            final String actual = calculatorr.calculate(expr);
            assertEquals(expctd, actual);
        } catch (NaNException e) {
            fail();
        }
    }

    @Test
    public void exprWithInfinityDividedByInfinity_calculate_throwsNaNException() {
        final CalculatorrImpl calculatorr = new CalculatorrImpl();
        final String expr = "-1+∞÷∞";
        try {
            calculatorr.calculate(expr);
        } catch (NaNException e) {
            return;
        }
        fail();
    }
}
