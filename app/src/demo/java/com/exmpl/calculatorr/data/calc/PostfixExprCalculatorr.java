package com.exmpl.calculatorr.data.calc;

import com.exmpl.calculatorr.data.excpt.NaNException;
import com.exmpl.calculatorr.data.model.Token;
import com.exmpl.calculatorr.data.model.expr.PostfixExpression;
import com.exmpl.calculatorr.data.model.oprnd.Operand;
import com.exmpl.calculatorr.data.model.oprtr.Binary;
import com.exmpl.calculatorr.data.model.oprtr.Operator;
import com.exmpl.calculatorr.data.model.oprtr.Unary;

import java.util.ArrayDeque;
import java.util.Deque;

public final class PostfixExprCalculatorr implements Calculatorr<PostfixExpression, Operand> {

    public PostfixExprCalculatorr() {
    }

    @SuppressWarnings("unchecked")
    @Override
    public Operand calculate(PostfixExpression expr) throws NaNException {
        final Deque<Operand> stack = new ArrayDeque<>();
        for (final Token tkn : expr) {
            if (tkn instanceof Operand) {
                stack.push((Operand) tkn);
            } else if (tkn instanceof Operator) {
                final Operator op = (Operator) tkn;
                final Operand rslt;
                if (op instanceof Unary<?, ?>) {
                    final Unary<Operand, Operand> unOp = (Unary<Operand, Operand>) op;
                    rslt = unOp.invoke(stack.pop());
                } else if (op instanceof Binary<?, ?, ?>) {
                    final Binary<Operand, Operand, Operand> binOp = (Binary<Operand, Operand, Operand>) op;
                    final Operand arg2 = stack.pop();
                    final Operand arg1 = stack.pop();
                    rslt = binOp.invoke(arg1, arg2);
                } else {
                    throw new IllegalStateException("Unknown operator");
                }
                stack.push(rslt);
            }
        }
        final Operand rslt = stack.pop();
        if (Double.isNaN(rslt.getValue())) {
            throw new NaNException();
        }
        return rslt;
    }
}
