package com.exmpl.calculatorr.data.calc;

import com.exmpl.calculatorr.data.model.expr.Expression;
import com.exmpl.calculatorr.data.model.expr.Notated;

public interface Parser<T extends Expression & Notated, R extends Expression & Notated> {

    R parse(T expr);
}
