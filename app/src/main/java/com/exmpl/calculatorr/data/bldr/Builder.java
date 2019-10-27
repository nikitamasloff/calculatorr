package com.exmpl.calculatorr.data.bldr;

import com.exmpl.calculatorr.data.excpt.BadExpressionException;
import com.exmpl.calculatorr.data.model.expr.Expression;
import com.exmpl.calculatorr.data.model.expr.Notated;
import com.exmpl.calculatorr.data.model.oprtr.Operator;
import com.exmpl.calculatorr.data.model.symb.Symbol;

import java.util.Collection;

public interface Builder<T extends Operator, S extends Symbol, R extends Expression & Notated> {

    Collection<Object> readOnlyCopy();

    void attach(T oprtr);

    void attach(S symb);

    void detach();

    boolean canBuild();

    R build() throws BadExpressionException;
}
