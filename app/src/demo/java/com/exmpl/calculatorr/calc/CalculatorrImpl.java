package com.exmpl.calculatorr.calc;

import com.exmpl.calculatorr.excpt.NaNException;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

import static com.exmpl.calculatorr.calc.Operatorz.isOperator;
import static com.exmpl.calculatorr.calc.Operatorz.precedenceOf;
import static com.exmpl.calculatorr.util.Utilz.isIn;

public class CalculatorrImpl implements Calculatorr {

    public CalculatorrImpl() {
    }

    @Override
    public String calculate(String expr) throws NaNException {
        final String[] parsd = parse(expr);
        final String[] postfx = toPostfx(parsd);
        final Deque<String> stck = new ArrayDeque<>();
        for (final String token : postfx) {
            if (isOperator(token)) {
                final String arg2 = stck.pop();
                final String arg1 = stck.pop();
                final String rslt = operate(token, arg1, arg2);
                stck.push(rslt);
            } else {
                stck.push(token);
            }
        }
        return stck.pop();
    }

    private static String operate(String oprtr, String oprnd1, String oprnd2) throws NaNException {
        if (!isOperator(oprtr)) {
            throw new IllegalArgumentException("Unknown operator");
        }
        oprnd1 = oprnd1.replace("∞", "Infinity");
        oprnd2 = oprnd2.replace("∞", "Infinity");
        final double a = Double.valueOf(oprnd1);
        final double b = Double.valueOf(oprnd2);
        final double rslt;
        switch (oprtr.charAt(0)) {
            case '+':
                rslt = a + b;
                break;
            case '-':
                rslt = a - b;
                break;
            case '×':
                rslt = a * b;
                break;
            case '÷':
                rslt = a / b;
                break;
            default:
                throw new IllegalArgumentException("Unknown operator");
        }
        if (Double.isNaN(rslt)) {
            throw new NaNException();
        } else if (Double.isInfinite(rslt)) {
            return Double.toString(rslt).replace("Infinity", "∞");
        }
        return Double.toString(rslt);
    }

    private static String[] parse(String expr) {
        if (expr == null || expr.isEmpty()) {
            return new String[0];
        }
        final Queue<String> out = new ArrayDeque<>();
        final StringBuilder oprnd = new StringBuilder();
        final int len = expr.length();
        for (int i = 0; i < len; ++i) {
            final char ch = expr.charAt(i);
            switch (ch) {
                case '-':
                    if (i == 0 || isIn("×÷", expr.charAt(i - 1))) {
                        oprnd.append(ch);
                        break;
                    }
                case '+':
                case '×':
                case '÷':
                    if (oprnd.length() != 0) {
                        out.add(oprnd.toString());
                        oprnd.setLength(0);
                    }
                    out.add(Character.toString(ch));
                    break;

                default:
                    oprnd.append(ch);
                    break;
            }
        }
        if (oprnd.length() != 0) {
            out.add(oprnd.toString());
        }
        return out.toArray(new String[0]);
    }

    private static String[] toPostfx(String[] infx) {
        final Deque<String> oprtrs = new ArrayDeque<>();
        final Queue<String> out = new ArrayDeque<>();
        for (final String token : infx) {
            if (isOperator(token)) {
                while (!oprtrs.isEmpty() && precedenceOf(oprtrs.peek()) >= precedenceOf(token)) {
                    out.add(oprtrs.pop());
                }
                oprtrs.push(token);
            } else {
                out.add(token);
            }
        }
        while (!oprtrs.isEmpty()) {
            out.add(oprtrs.pop());
        }
        return out.toArray(new String[0]);
    }
}
