package com.exmpl.calculatorr.data.calc;

import com.exmpl.calculatorr.data.model.Token;
import com.exmpl.calculatorr.data.model.expr.Expression;
import com.exmpl.calculatorr.data.model.expr.PostfixExpression;
import com.exmpl.calculatorr.data.model.oprnd.Operand;
import com.exmpl.calculatorr.data.model.oprtr.LeftAssociative;
import com.exmpl.calculatorr.data.model.oprtr.Operator;

import java.util.ArrayDeque;
import java.util.Deque;

public final class ShuntingYardParser implements Parser {

    public ShuntingYardParser() {
    }

    @Override
    public Expression parse(Expression expr) {
        final Deque<Operator> ops = new ArrayDeque<>();
        final Deque<Token> out = new ArrayDeque<>();
        for (final Token token : expr) {
            if (token instanceof Operand) {
                out.push(token);
            } else if (token instanceof Operator) {
                final Operator op = (Operator) token;
                Operator top;
                while ((top = ops.peek()) != null &&
                        (op.getPrecedence() < top.getPrecedence()
                                || op.getPrecedence() == top.getPrecedence() && op instanceof LeftAssociative)) {
                    out.push(ops.pop());
                }
            }
        }
        return new PostfixExpression(out);
    }
}
