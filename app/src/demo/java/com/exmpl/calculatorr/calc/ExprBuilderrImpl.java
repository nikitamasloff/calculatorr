package com.exmpl.calculatorr.calc;

import com.exmpl.calculatorr.excpt.BadExprException;
import com.exmpl.calculatorr.excpt.TooShortExprException;

import static com.exmpl.calculatorr.calc.Tokenz.isToken;
import static com.exmpl.calculatorr.util.Utilz.isDigit;
import static com.exmpl.calculatorr.util.Utilz.isDigitOrPoint;
import static com.exmpl.calculatorr.util.Utilz.isDigitsOnly;
import static com.exmpl.calculatorr.util.Utilz.isIn;
import static com.exmpl.calculatorr.util.Utilz.isInAnyOf;
import static com.exmpl.calculatorr.util.Utilz.lastIn;
import static com.exmpl.calculatorr.util.Utilz.secondToLastIn;

public class ExprBuilderrImpl implements ExprBuilderr {

    private final StringBuilder expr;

    public ExprBuilderrImpl() {
        this.expr = new StringBuilder();
    }

    public ExprBuilderrImpl(String init) {
        this();
        set(init);
    }

    @Override
    public void set(String src) {
        expr.setLength(0);
        if (src == null || src.isEmpty()) {
            return;
        }
        final int len = src.length();
        for (int i = 0; i < len; ++i) {
            final char ch = src.charAt(i);
            if (!isToken(ch)) {
                throw new IllegalArgumentException("Unknown token");
            }
            switch (ch) {
                case '-':
                    if (i == 0 && len != 1) {
                        break;
                    }
                case '+':
                case '×':
                case '÷':
                    throw new IllegalArgumentException("Only immediate values are allowed as source");

                case '0':
                    if (i != 0 && expr.charAt(i - 1) == '0'
                            && (i == 1 || !isDigitOrPoint(expr.charAt(i - 2)))) {
                        throw new IllegalArgumentException("Invalid source value: double '0'");
                    } else {
                        break;
                    }

                case '.':
                    if (isIn(expr, '.')) {
                        throw new IllegalArgumentException("Invalid source value: double '.'");
                    } else {
                        break;
                    }

                case '∞':
                    if (isIn(expr, '∞')) {
                        throw new IllegalArgumentException("Invalid source value: double '∞'");
                    } else {
                        break;
                    }
            }
            expr.append(ch);
        }
    }

    @Override
    public boolean attach(String token) {
        final char tkn;
        if (token == null || token.length() != 1 || !isToken(tkn = token.charAt(0))) {
            throw new IllegalArgumentException("Unknown or null token");
        }
        final int len = expr.length();
        switch (tkn) {
            case '∞':
                throw new IllegalArgumentException("Cannot attach Infinity");

            case '+':
            case '×':
            case '÷':
                if (len != 0) {
                    final char last = lastIn(expr);
                    if (isDigitOrPoint(last) || last == '∞') {
                        expr.append(tkn);
                        return true;
                    } else if (isIn("+×÷", last) && tkn != last) {
                        replace1(tkn);
                        return true;
                    } else if (last == '-') {
                        if (len == 1)
                            return false;
                        final char last2 = secondToLastIn(expr);
                        if (isDigitOrPoint(last2) || last2 == '∞') {
                            replace1(tkn);
                            return true;
                        } else if (isIn("×÷", last2) && tkn != last2) {
                            replace2(tkn);
                            return true;
                        }
                    }
                }
                break;

            case '-':
                final char last;
                if (len == 0 || isDigitOrPoint(last = lastIn(expr)) || last == '∞' || isIn("×÷", last)) {
                    expr.append(tkn);
                    return true;
                } else if (last == '+') {
                    replace1(tkn);
                    return true;
                }
                break;

            case '.':
                if (len == 0) {
                    expr.append(tkn);
                    return true;
                } else if (lastIn(expr) == '∞') {
                    return false;
                }
                final int lasti = expr.lastIndexOf(".");
                final String substr;
                if (lasti == -1 ||
                        (!(substr = expr.substring(lasti + 1)).isEmpty() && !isDigitsOnly(substr))) {
                    expr.append(tkn);
                    return true;
                }
                break;

            case '0':
                if (len != 0 && lastIn(expr) == '0'
                        && (len == 1 || !isDigitOrPoint(secondToLastIn(expr)))) {
                    return false;
                }
            default:
                if (len != 0 && lastIn(expr) == '∞') {
                    return false;
                }
                expr.append(tkn);
                return true;
        }
        return false;
    }

    private void replace1(char tkn) {
        final int len = expr.length();
        if (len < 1)
            throw new IllegalStateException("Not enough tokens to replace");
        expr.setCharAt(len - 1, tkn);
    }

    private void replace2(char tkn) {
        final int len = expr.length();
        if (len < 2)
            throw new IllegalStateException("Not enough tokens to replace");
        expr.setCharAt(len - 2, tkn);
        expr.setLength(len - 1);
    }

    @Override
    public boolean detach() {
        final int len = expr.length();
        if (len != 0) {
            expr.setLength(len - 1);
            return true;
        }
        return false;
    }

    @Override
    public String build() throws TooShortExprException, BadExprException {
        final int len = expr.length();
        if (len == 0 || (!isInAnyOf(expr, "+×÷")
                && (!isIn(expr, '-') || (expr.lastIndexOf("-") == 0) && len != 1))) {
            throw new TooShortExprException();
        }
        final char last = expr.charAt(len - 1);
        if (isIn("+-×÷", last)) {
            throw new BadExprException();
        }
        for (int i = 0; i < expr.length(); ++i) {
            if (expr.charAt(i) == '.'
                    && (i == 0 || !isDigit(expr.charAt(i - 1)))) {
                expr.insert(i++, '0');
            }
            if (expr.charAt(i) == '.'
                    && (i == expr.length() - 1 || !isDigit(expr.charAt(i + 1)))) {
                expr.insert(++i, '0');
            }
        }
        return expr.toString();
    }
}
