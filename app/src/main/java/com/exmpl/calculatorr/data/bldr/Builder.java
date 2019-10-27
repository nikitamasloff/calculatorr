package com.exmpl.calculatorr.data.bldr;

import com.exmpl.calculatorr.data.model.expr.Expression;
import com.exmpl.calculatorr.data.model.expr.Notated;
import com.exmpl.calculatorr.data.model.oprtr.Operator;
import com.exmpl.calculatorr.data.model.symb.Symbol;

import java.util.Collection;

public interface Builder<T extends Operator, S extends Symbol, R extends Expression & Notated> {

    int RESULT_APPENDED = 1;
    int RESULT_IGNORED = 0;
    int RESULT_REPLACED = -1;
    int RESULT_REPLACED_2 = -2;

    Collection<Object> readOnlyCopy();

    int attach(T oprtr);

    boolean attach(S symb);

    boolean detach();

    boolean canBuild();

    R build();
}
