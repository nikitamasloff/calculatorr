package com.exmpl.calculatorr.calc;

import com.exmpl.calculatorr.excpt.BadExprException;
import com.exmpl.calculatorr.excpt.TooShortExprException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public final class ExprBuilderrImplTest {

    @Test
    public void setNull_emptyValueSet() {
        try {
            final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl(null);
            assertEquals("", exprBuilderr.getExpr().toString());
        } catch (Throwable t) {
            fail();
        }
    }

    @Test
    public void setEmptyValue_emptyValueSet() {
        try {
            final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl("");
            assertEquals("", exprBuilderr.getExpr().toString());
        } catch (Throwable t) {
            fail();
        }
    }

    @Test
    public void setSingleUnaryMinus_throwsRuntimeException() {
        try {
            final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl("-");
        } catch (RuntimeException e) {
            return;
        } catch (Throwable t) {
            fail();
        }
        fail();
    }

    @Test
    public void setPositiveInteger_valueSet() {
        try {
            final String initValue = "123";
            final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl(initValue);
            assertEquals(initValue, exprBuilderr.getExpr().toString());
        } catch (Throwable t) {
            fail();
        }
    }

    @Test
    public void setPositiveFloat_valueSet() {
        try {
            final String initValue = "123.4";
            final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl(initValue);
            assertEquals(initValue, exprBuilderr.getExpr().toString());
        } catch (Throwable t) {
            fail();
        }
    }

    @Test
    public void setNegativeInteger_valueSet() {
        try {
            final String initValue = "-123";
            final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl(initValue);
            assertEquals(initValue, exprBuilderr.getExpr().toString());
        } catch (Throwable t) {
            fail();
        }
    }

    @Test
    public void setNegativeFloat_valueSet() {
        try {
            final String initValue = "-123.4";
            final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl(initValue);
            assertEquals(initValue, exprBuilderr.getExpr().toString());
        } catch (Throwable t) {
            fail();
        }
    }

    @Test
    public void setPositiveInfinity_valueSet() {
        try {
            final String initValue = "∞";
            final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl(initValue);
            assertEquals(initValue, exprBuilderr.getExpr().toString());
        } catch (Throwable t) {
            fail();
        }
    }

    @Test
    public void setNegativeInfinity_valueSet() {
        try {
            final String initValue = "-∞";
            final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl(initValue);
            assertEquals(initValue, exprBuilderr.getExpr().toString());
        } catch (Throwable t) {
            fail();
        }
    }

    @Test
    public void setNegativeValueWithDouble0InIntegerPart_valueSet() {
        try {
            final String initValue = "-100.23";
            final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl(initValue);
            assertEquals(initValue, exprBuilderr.getExpr().toString());
        } catch (Throwable t) {
            fail();
        }
    }

    @Test
    public void setPositiveValueWithDouble0InIntegerPart_valueSet() {
        try {
            final String initValue = "100.23";
            final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl(initValue);
            assertEquals(initValue, exprBuilderr.getExpr().toString());
        } catch (Throwable t) {
            fail();
        }
    }

    @Test
    public void setValueWithDouble0InFractionPart_valueSet() {
        try {
            final String initValue = "-1.0023";
            final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl(initValue);
            assertEquals(initValue, exprBuilderr.getExpr().toString());
        } catch (Throwable t) {
            fail();
        }
    }

    @Test
    public void setValueWithUnknownToken_throwsRuntimeException() {
        try {
            final String initValue = "123.A56";
            final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl(initValue);
        } catch (RuntimeException e) {
            return;
        }
        fail();
    }

    @Test
    public void setValueWithBinaryOperator_throwsRuntimeException() {
        try {
            final String initValue = "+3";
            final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl(initValue);
        } catch (RuntimeException e) {
            return;
        }
        fail();
    }

    @Test
    public void setPositiveValueWithDouble0AsOnlyIntegerPart_throwsRuntimeException() {
        try {
            final String initValue = "-00.12";
            final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl(initValue);
        } catch (RuntimeException e) {
            return;
        }
        fail();
    }

    @Test
    public void setNegativeValueWithDouble0AsOnlyIntegerPart_throwsRuntimeException() {
        try {
            final String initValue = "00.12";
            final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl(initValue);
        } catch (RuntimeException e) {
            return;
        }
        fail();
    }

    @Test
    public void setValueWithDoublePoint_throwsRuntimeException() {
        try {
            final String initValue = "123.456.789";
            final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl(initValue);
        } catch (RuntimeException e) {
            return;
        }
        fail();
    }

    @Test
    public void setValueWithDoubleInfinity_throwsRuntimeException() {
        try {
            final String initValue = "-∞∞";
            final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl(initValue);
        } catch (RuntimeException e) {
            return;
        } catch (Throwable t) {
            fail();
        }
        fail();
    }

    @Test
    public void emptyExpr_attachNull_throwsRuntimeException() {
        try {
            final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl();
            exprBuilderr.attach(null);
        } catch (RuntimeException e) {
            return;
        } catch (Throwable t) {
            fail();
        }
        fail();
    }

    @Test
    public void emptyExpr_attachTokenWithLengthNotEqual1_throwsRuntimeException() {
        try {
            final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl();
            exprBuilderr.attach("++");
        } catch (RuntimeException e) {
            return;
        } catch (Throwable t) {
            fail();
        }
        fail();
    }

    @Test
    public void emptyExpr_attachUnknownTokenOfLength1_throwsRuntimeException() {
        try {
            final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl();
            exprBuilderr.attach("&");
        } catch (RuntimeException e) {
            return;
        } catch (Throwable t) {
            fail();
        }
        fail();
    }

    @Test
    public void emptyExpr_attachInfinity_throwsRuntimeException() {
        try {
            final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl();
            exprBuilderr.attach("∞");
        } catch (RuntimeException e) {
            return;
        } catch (Throwable t) {
            fail();
        }
        fail();
    }

    @Test
    public void emptyExpr_attachBinaryOperator_ignored() {
        final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl();
        final boolean rslt = exprBuilderr.attach("+");
        assertFalse(rslt);
        assertEquals(exprBuilderr.getExpr().toString(), "");
    }

    @Test
    public void exprEndingWithDigit_attachBinaryOperator_appended() {
        final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl("123");
        final boolean rslt = exprBuilderr.attach("+");
        assertTrue(rslt);
        assertEquals(exprBuilderr.getExpr().toString(), "123+");
    }

    @Test
    public void exprEndingWithPoint_attachBinaryOperator_appended() {
        final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl("123.");
        final boolean rslt = exprBuilderr.attach("+");
        assertTrue(rslt);
        assertEquals(exprBuilderr.getExpr().toString(), "123.+");
    }

    @Test
    public void exprEndingWithInfinity_attachBinaryOperator_appended() {
        final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl("-∞");
        final boolean rslt = exprBuilderr.attach("+");
        assertTrue(rslt);
        assertEquals(exprBuilderr.getExpr().toString(), "-∞+");
    }

    @Test
    public void exprEndingWithBinaryOperator_attachAnotherBinaryOperator_replaced1() {
        final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl("123");
        exprBuilderr.attach("+");
        final boolean rslt = exprBuilderr.attach("×");
        assertTrue(rslt);
        assertEquals(exprBuilderr.getExpr().toString(), "123×");
    }

    @Test
    public void exprEndingWithBinaryOperator_attachSameOperator_ignored() {
        final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl("123");
        exprBuilderr.attach("+");
        final boolean rslt = exprBuilderr.attach("+");
        assertFalse(rslt);
        assertEquals(exprBuilderr.getExpr().toString(), "123+");
    }

    @Test
    public void exprEndingWithBinaryOperatorAndMinus_attachOtherBinaryOperator_replaced2() {
        final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl("123");
        exprBuilderr.attach("×");
        exprBuilderr.attach("-");
        final boolean rslt = exprBuilderr.attach("÷");
        assertTrue(rslt);
        assertEquals(exprBuilderr.getExpr().toString(), "123÷");
    }

    @Test
    public void exprWithSingleUnaryMinus_attachBinaryOperator_ignored() {
        final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl();
        exprBuilderr.attach("-");
        final boolean rslt = exprBuilderr.attach("+");
        assertFalse(rslt);
        assertEquals(exprBuilderr.getExpr().toString(), "-");
    }

    @Test
    public void exprEndingWithMinus_attachPlus_replaced1() {
        final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl("123");
        exprBuilderr.attach("-");
        final boolean rslt = exprBuilderr.attach("+");
        assertTrue(rslt);
        assertEquals(exprBuilderr.getExpr().toString(), "123+");
    }

    @Test
    public void exprEndingWithMinus_attachBinaryOperator_replaced1() {
        final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl("123");
        exprBuilderr.attach("-");
        final boolean rslt = exprBuilderr.attach("×");
        assertTrue(rslt);
        assertEquals(exprBuilderr.getExpr().toString(), "123×");
    }

    @Test
    public void emptyExpr_attachMinus_appended() {
        final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl();
        final boolean rslt = exprBuilderr.attach("-");
        assertTrue(rslt);
        assertEquals(exprBuilderr.getExpr().toString(), "-");
    }

    @Test
    public void exprEndingWithDigit_attachMinus_attached() {
        final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl("123");
        final boolean rslt = exprBuilderr.attach("-");
        assertTrue(rslt);
        assertEquals(exprBuilderr.getExpr().toString(), "123-");
    }

    @Test
    public void exprEndingWithPoint_attachMinus_attached() {
        final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl("123.");
        final boolean rslt = exprBuilderr.attach("-");
        assertTrue(rslt);
        assertEquals(exprBuilderr.getExpr().toString(), "123.-");
    }

    @Test
    public void exprEndingWithInfinity_attachMinus_attached() {
        final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl("∞");
        final boolean rslt = exprBuilderr.attach("-");
        assertTrue(rslt);
        assertEquals(exprBuilderr.getExpr().toString(), "∞-");
    }

    @Test
    public void exprEndingWithMultiplyOrDivide_attachMinus_attached() {
        final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl("123");
        exprBuilderr.attach("×");
        final boolean rslt = exprBuilderr.attach("-");
        assertTrue(rslt);
        assertEquals(exprBuilderr.getExpr().toString(), "123×-");
    }

    @Test
    public void exprEndingWithPlus_attachMinus_replaced1() {
        final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl("123");
        exprBuilderr.attach("+");
        final boolean rslt = exprBuilderr.attach("-");
        assertTrue(rslt);
        assertEquals(exprBuilderr.getExpr().toString(), "123-");
    }

    @Test
    public void exprEndingWithInfinity_attachPoint_ignored() {
        final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl("∞");
        final boolean rslt = exprBuilderr.attach(".");
        assertFalse(rslt);
        assertEquals(exprBuilderr.getExpr().toString(), "∞");
    }

    @Test
    public void exprEndingWithPoint_attachPoint_ignored() {
        final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl("123.");
        final boolean rslt = exprBuilderr.attach(".");
        assertFalse(rslt);
        assertEquals(exprBuilderr.getExpr().toString(), "123.");
    }

    @Test
    public void exprEndingWithFloatValue_attachPoint_ignored() {
        final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl("123.56");
        final boolean rslt = exprBuilderr.attach(".");
        assertFalse(rslt);
        assertEquals(exprBuilderr.getExpr().toString(), "123.56");
    }

    @Test
    public void exprEndingWithOperator_attachPoint_appended() {
        final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl("123.56");
        exprBuilderr.attach("+");
        final boolean rslt = exprBuilderr.attach(".");
        assertTrue(rslt);
        assertEquals(exprBuilderr.getExpr().toString(), "123.56+.");
    }

    @Test
    public void exprEndingWithSingleZero_attachZero_ignored() {
        final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl("0");
        final boolean rslt = exprBuilderr.attach("0");
        assertFalse(rslt);
        assertEquals(exprBuilderr.getExpr().toString(), "0");
    }

    @Test
    public void exprEndingWithIntegerValueEndingWithZero_attachZero_appended() {
        final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl("10");
        final boolean rslt = exprBuilderr.attach("0");
        assertTrue(rslt);
        assertEquals(exprBuilderr.getExpr().toString(), "100");
    }

    @Test
    public void exprEndingWithFloatValueEndingWithZero_attachZero_appended() {
        final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl("123.0");
        final boolean rslt = exprBuilderr.attach("0");
        assertTrue(rslt);
        assertEquals(exprBuilderr.getExpr().toString(), "123.00");
    }

    @Test
    public void exprEndingWithInfinity_attachDigit_ignored() {
        final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl("∞");
        final boolean rslt = exprBuilderr.attach("1");
        assertFalse(rslt);
        assertEquals(exprBuilderr.getExpr().toString(), "∞");
    }

    @Test
    public void exprEndingWithOperator_attachDigit_appended() {
        final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl("123");
        exprBuilderr.attach("+");
        final boolean rslt = exprBuilderr.attach("1");
        assertTrue(rslt);
        assertEquals(exprBuilderr.getExpr().toString(), "123+1");
    }

    @Test
    public void exprEndingWithDigit_attachDigit_appended() {
        final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl("123");
        final boolean rslt = exprBuilderr.attach("1");
        assertTrue(rslt);
        assertEquals(exprBuilderr.getExpr().toString(), "1231");
    }

    @Test
    public void exprEndingWithDigit_detach_detached() {
        final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl("123");
        final boolean rslt = exprBuilderr.detach();
        assertTrue(rslt);
        assertEquals(exprBuilderr.getExpr().toString(), "12");
    }

    @Test
    public void exprEndingWithOperator_detach_detached() {
        final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl("123");
        exprBuilderr.attach("+");
        final boolean rslt = exprBuilderr.detach();
        assertTrue(rslt);
        assertEquals(exprBuilderr.getExpr().toString(), "123");
    }

    @Test
    public void emptyExpr_detach_ignored() {
        final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl();
        final boolean rslt = exprBuilderr.detach();
        assertFalse(rslt);
        assertEquals(exprBuilderr.getExpr().toString(), "");
    }

    @Test
    public void emptyExpr_build_throwsTooShortExprException() {
        try {
            final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl();
            exprBuilderr.build();
        } catch (TooShortExprException e) {
            return;
        } catch (Throwable t) {
            fail();
        }
        fail();
    }

    @Test
    public void exprWithOnlyImmediateValue_build_throwsTooShortExprException() {
        try {
            final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl("123");
            exprBuilderr.build();
        } catch (TooShortExprException e) {
            return;
        } catch (Throwable t) {
            fail();
        }
        fail();
    }

    @Test
    public void exprWithNegativeValue_build_throwsTooShortExprException() {
        try {
            final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl("-123.56");
            exprBuilderr.build();
        } catch (TooShortExprException e) {
            return;
        } catch (Throwable t) {
            fail();
        }
        fail();
    }

    @Test
    public void exprWithOnlyUnaryMinus_build_throwsBadExprException() {
        try {
            final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl();
            exprBuilderr.attach("-");
            exprBuilderr.build();
        } catch (BadExprException e) {
            return;
        } catch (Throwable t) {
            fail();
        }
        fail();
    }

    @Test
    public void exprEndingWithOperator_build_throwsBadExprException() {
        try {
            final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl("123");
            exprBuilderr.attach("×");
            exprBuilderr.build();
        } catch (BadExprException e) {
            return;
        } catch (Throwable t) {
            fail();
        }
        fail();
    }

    @Test
    public void exprWithPoint_build_zerosInserted() {
        final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl("0");
        exprBuilderr.attach("+");
        exprBuilderr.attach(".");
        try {
            assertEquals("0+0.0", exprBuilderr.build());
        } catch (Throwable t) {
            fail();
        }
    }

    @Test
    public void exprWithDigitAndPoint_build_zerosInserted() {
        final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl("0");
        exprBuilderr.attach("+");
        exprBuilderr.attach("1");
        exprBuilderr.attach(".");
        try {
            assertEquals("0+1.0", exprBuilderr.build());
        } catch (Throwable t) {
            fail();
        }
    }

    @Test
    public void exprWithPointAndDigit_build_zerosInserted() {
        final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl("0");
        exprBuilderr.attach("+");
        exprBuilderr.attach(".");
        exprBuilderr.attach("1");
        try {
            assertEquals("0+0.1", exprBuilderr.build());
        } catch (Throwable t) {
            fail();
        }
    }

    @Test
    public void expr_build_built() {
        final ExprBuilderrImpl exprBuilderr = new ExprBuilderrImpl();
        exprBuilderr.attach("1");
        exprBuilderr.attach("+");
        exprBuilderr.attach("2");
        exprBuilderr.attach("×");
        exprBuilderr.attach("3");
        try {
            assertEquals("1+2×3", exprBuilderr.build());
        } catch (Throwable t) {
            fail();
        }
    }
}
