package com.exmpl.calculatorr.data.bldr;

import com.exmpl.calculatorr.data.excpt.BadExpressionException;
import com.exmpl.calculatorr.data.model.Token;
import com.exmpl.calculatorr.data.model.expr.InfixExpression;
import com.exmpl.calculatorr.data.model.oprnd.Operand;
import com.exmpl.calculatorr.data.model.oprtr.Binary;
import com.exmpl.calculatorr.data.model.oprtr.Infix;
import com.exmpl.calculatorr.data.model.oprtr.MinusOperator;
import com.exmpl.calculatorr.data.model.oprtr.Operator;
import com.exmpl.calculatorr.data.model.oprtr.Prefix;
import com.exmpl.calculatorr.data.model.oprtr.Unary;
import com.exmpl.calculatorr.data.model.oprtr.UnaryMinusOperator;
import com.exmpl.calculatorr.data.model.symb.DecimalSymbol;
import com.exmpl.calculatorr.data.model.symb.Infinity;
import com.exmpl.calculatorr.data.model.symb.Minus;
import com.exmpl.calculatorr.data.model.symb.Point;
import com.exmpl.calculatorr.data.model.symb.Zero;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public final class BuilderImpl implements Builder<Operator, DecimalSymbol, InfixExpression> {

    private final LinkedList<Object> seq;
    private final DecimalSymbol.Factory factory;

    public BuilderImpl(DecimalSymbol.Factory factory, Operand initialValue) {
        Objects.requireNonNull(factory);
        if (initialValue != null && Double.isNaN(initialValue.getValue())) {
            throw new IllegalArgumentException("Initial value cannot be NaN");
        }
        this.factory = factory;
        this.seq = initialValue != null ? new LinkedList<>(parse(initialValue)) : new LinkedList<>();
    }

    public BuilderImpl(DecimalSymbol.Factory factory) {
        this(factory, null);
    }

    @Override
    public Collection<Object> readOnlyCopy() {
        return Collections.unmodifiableCollection(seq);
    }

    @Override
    public int attach(Operator oprtr) {
        Objects.requireNonNull(oprtr);
        if (seq.isEmpty()) {
            if (oprtr instanceof MinusOperator) {
                seq.push(new UnaryMinusOperator());
                return RESULT_APPENDED;
            } else {
                return RESULT_IGNORED;
            }
        }
        final Object top = seq.peek();
        if (oprtr instanceof Binary<?, ?, ?>) {
            if (top instanceof DecimalSymbol) {
                seq.push(oprtr);
                return RESULT_APPENDED;
            } else if (top instanceof Binary<?, ?, ?>) {
                if (!oprtr.equals(top)) {
                    seq.pop();
                    seq.push(oprtr);
                    return RESULT_REPLACED;
                } else {
                    return RESULT_IGNORED;
                }
            } else if (top instanceof Unary<?, ?>) {
                final int size = seq.size();
                if (size == 1 || seq.get(size - 2).equals(oprtr)) {
                    return RESULT_IGNORED;
                } else {
                    seq.pop();
                    seq.pop();
                    seq.push(oprtr);
                    return RESULT_REPLACED_2;
                }
            }
        } else if (oprtr instanceof Unary<?, ?>) {
            if (top instanceof Binary<?, ?, ?>) {
                seq.push(oprtr);
                return RESULT_APPENDED;
            }
        }
        return RESULT_IGNORED;
    }

    private boolean canAttach(DecimalSymbol symb) {
        if (seq.isEmpty())
            return true;
        if (seq.peek() instanceof Infinity)
            return false;
        if (symb instanceof Point) {
            boolean fl = false;
            Object obj;
            for (int i = seq.size() - 1; i >= 0 && (obj = seq.get(i)) instanceof DecimalSymbol; i--) {
                if (obj instanceof Point) {
                    if (!fl)
                        fl = true;
                    else
                        return false;
                }
            }
        }
        if (symb instanceof Zero) {
            boolean fl = false;
            Object obj;
            for (int i = seq.size() - 1; i >= 0 && (obj = seq.get(i)) instanceof DecimalSymbol; i--) {
                if (obj instanceof Point) {
                    return true;
                } else if (obj instanceof Zero) {
                    fl = true;
                }
            }
            return !fl;
        }
        return true;
    }

    @Override
    public boolean attach(DecimalSymbol symb) {
        if (symb instanceof Infinity || symb instanceof Minus) {
            throw new IllegalArgumentException("Cannot attach Infinity or Minus");
        }
        if (canAttach(symb)) {
            seq.push(symb);
            return true;
        } else
            return false;
    }

    @Override
    public boolean detach() {
        if (seq.isEmpty()) {
            return false;
        }
        final Object obj = seq.pop();
        if (obj instanceof Infinity && seq.peek() instanceof Minus) {
            seq.pop();
        }
        return true;
    }

    @Override
    public boolean canBuild() {
        if (seq.isEmpty()) {
            return false;
        }
        final Object top = seq.peek();
        return !(top instanceof Prefix) && !(top instanceof Infix);
    }

    @Override
    public InfixExpression build() {
        if (!canBuild()) {
            throw new BadExpressionException();
        }
        final Deque<Token> acc = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for (final Object obj : seq) {
            if (obj instanceof DecimalSymbol) {
                sb.append(((DecimalSymbol) obj).getValue());
            } else {
                if (sb.length() != 0) {
                    if (sb.length() == 1 && sb.charAt(0) == Point.getInstance().getValue()) {
                        sb.append(Zero.getInstance().getValue());
                    }
                    final double dbl = Double.valueOf(sb.toString());
                    final Operand oprnd = new Operand(dbl);
                    acc.push(oprnd);
                    sb = new StringBuilder();
                }
            }
            if (obj instanceof Operator) {
                acc.push((Token) obj);
            }
        }
        return new InfixExpression(acc);
    }

    private List<Object> parse(Operand operand) {
        if (operand == null || Double.isNaN(operand.getValue())) {
            throw new IllegalArgumentException("Cannot parse null or NaN");
        }
        final double val = operand.getValue();
        if (Double.isInfinite(val)) {
            if (val < 0) {
                return Arrays.asList((Object) Minus.getInstance(), Infinity.getInstance());
            } else {
                return Collections.singletonList((Object) Infinity.getInstance());
            }
        }
        final String repr = Double.toString(operand.getValue());
        final int len = repr.length();
        final List<Object> acc = new ArrayList<>(len);
        for (int i = 0; i < len; ++i) {
            final DecimalSymbol dsymb = factory.fromChar(repr.charAt(i));
            acc.add(dsymb);
        }
        return acc;
    }
}
