package com.exmpl.calculatorr.data.model.expr;

import com.exmpl.calculatorr.data.model.Token;

import java.util.Collection;

public class InfixExpression extends Expression implements Infix {

    public InfixExpression(Collection<Token> tokens) {
        super(tokens);
    }
}
