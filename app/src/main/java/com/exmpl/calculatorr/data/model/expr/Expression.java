package com.exmpl.calculatorr.data.model.expr;

import com.exmpl.calculatorr.data.model.Token;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;

public abstract class Expression implements Iterable<Token> {

    private final Collection<Token> tokens;

    public Expression(Collection<Token> tokens) {
        Objects.requireNonNull(tokens);
        this.tokens = Collections.unmodifiableCollection(tokens);
    }

    @Override
    public Iterator<Token> iterator() {
        return tokens.iterator();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof Expression) {
            final Expression expr = (Expression) obj;
            return this.tokens.equals(expr.tokens);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return tokens.hashCode();
    }
}
