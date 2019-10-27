package com.exmpl.calculatorr.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.exmpl.calculatorr.data.bldr.Builder;
import com.exmpl.calculatorr.data.calc.Calculatorr;
import com.exmpl.calculatorr.data.calc.Parser;
import com.exmpl.calculatorr.data.model.expr.InfixExpression;
import com.exmpl.calculatorr.data.model.expr.PostfixExpression;
import com.exmpl.calculatorr.data.model.oprnd.Operand;
import com.exmpl.calculatorr.data.model.oprtr.DivideOperator;
import com.exmpl.calculatorr.data.model.oprtr.MinusOperator;
import com.exmpl.calculatorr.data.model.oprtr.MultiplyOperator;
import com.exmpl.calculatorr.data.model.oprtr.Operator;
import com.exmpl.calculatorr.data.model.oprtr.PlusOperator;
import com.exmpl.calculatorr.data.model.oprtr.UnaryMinusOperator;
import com.exmpl.calculatorr.data.model.symb.DecimalSymbol;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public final class MainViewModel extends CalculatorrViewModel<Operator, Operand> {

    public MainViewModel(Builder<Operator, DecimalSymbol, InfixExpression> builder,
                         Parser<InfixExpression, PostfixExpression> parser,
                         Calculatorr<PostfixExpression, Operand> calculatorr) {
        super(builder, parser, calculatorr);
    }

    public static class Factory implements ViewModelProvider.Factory {

        private final Builder<Operator, DecimalSymbol, InfixExpression> builder;
        private final Parser<InfixExpression, PostfixExpression> parser;
        private final Calculatorr<PostfixExpression, Operand> calculatorr;

        public Factory(Builder<Operator, DecimalSymbol, InfixExpression> builder,
                       Parser<InfixExpression, PostfixExpression> parser,
                       Calculatorr<PostfixExpression, Operand> calculatorr) {
            Objects.requireNonNull(builder);
            Objects.requireNonNull(parser);
            Objects.requireNonNull(calculatorr);
            this.builder = builder;
            this.parser = parser;
            this.calculatorr = calculatorr;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            try {
                return modelClass.getConstructor(Builder.class, Parser.class, Calculatorr.class)
                        .newInstance(builder, parser, calculatorr);
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                throw new IllegalStateException("Unknown viewmodel class");
            }
        }
    }

    @Override
    protected String getOperatorRepresentation(Operator oprtr) {
        Objects.requireNonNull(oprtr);
        if (oprtr instanceof MinusOperator || oprtr instanceof UnaryMinusOperator)
            return "-";
        else if (oprtr instanceof PlusOperator)
            return "+";
        else if (oprtr instanceof DivideOperator)
            return "/";
        else if (oprtr instanceof MultiplyOperator)
            return "×";
        else
            throw new IllegalArgumentException("Unknown operator");
    }

    @Override
    protected String getOperandRepresentation(Operand oprnd) {
        final double val = oprnd.getValue();
        if (Double.isNaN(val)) {
            throw new IllegalArgumentException("Cannot represent NaN");
        }
        if (Double.isInfinite(val)) {
            if (val < 0)
                return "-∞";
            else
                return "∞";
        } else {
            return Double.toString(val);
        }
    }
}
