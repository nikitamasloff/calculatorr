package com.exmpl.calculatorr.data.model.expr;

import com.exmpl.calculatorr.data.model.Token;

import java.util.Collection;

public class PostfixExpression extends Expression implements Postfix {

    public PostfixExpression(Collection<Token> tokens) {
        super(tokens);
    }
}
