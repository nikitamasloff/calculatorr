package com.exmpl.calculatorr.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.exmpl.calculatorr.data.bldr.Builder;
import com.exmpl.calculatorr.data.calc.Calculatorr;
import com.exmpl.calculatorr.data.calc.Parser;
import com.exmpl.calculatorr.data.excpt.BadExpressionException;
import com.exmpl.calculatorr.data.excpt.NaNException;
import com.exmpl.calculatorr.data.model.expr.InfixExpression;
import com.exmpl.calculatorr.data.model.expr.PostfixExpression;
import com.exmpl.calculatorr.data.model.oprnd.Operand;
import com.exmpl.calculatorr.data.model.oprtr.Operator;
import com.exmpl.calculatorr.data.model.symb.DecimalSymbol;

import java.util.Collection;

public abstract class CalculatorrViewModel<T extends Operator, S extends Operand> extends ViewModel {

    private final Builder<T, DecimalSymbol, InfixExpression> builder;
    private final Parser<InfixExpression, PostfixExpression> parser;
    private final Calculatorr<PostfixExpression, S> calculatorr;

    private final MutableLiveData<String> expr;
    private final MutableLiveData<String> rslt;
    private final MutableLiveData<Boolean> isError;

    public CalculatorrViewModel(Builder<T, DecimalSymbol, InfixExpression> builder,
                                Parser<InfixExpression, PostfixExpression> parser,
                                Calculatorr<PostfixExpression, S> calculatorr) {
        this.builder = builder;
        this.parser = parser;
        this.calculatorr = calculatorr;

        this.expr = new MutableLiveData<>("");
        this.rslt = new MutableLiveData<>("");
        this.isError = new MutableLiveData<>(false);
    }

    public LiveData<String> getExpression() {
        return expr;
    }

    public LiveData<String> getResult() {
        return rslt;
    }

    public LiveData<Boolean> getIsError() {
        return isError;
    }

    protected Builder<T, DecimalSymbol, InfixExpression> getBuilder() {
        return builder;
    }

    protected Parser<InfixExpression, PostfixExpression> getParser() {
        return parser;
    }

    protected Calculatorr<PostfixExpression, S> getCalculatorr() {
        return calculatorr;
    }

    public void attach(DecimalSymbol symb) {
        if (builder.attach(symb)) {
            updateExpression();
            tryToCalculate();
        }
    }

    public void attach(T oprtr) {
        if (builder.attach(oprtr) != Builder.RESULT_IGNORED) {
            updateExpression();
            tryToCalculate();
        }
    }

    public void detach() {
        if (builder.detach()) {
            updateExpression();
            tryToCalculate();
        }
    }

    public void calculate() {
        String repr;
        boolean isError;
        try {
            final S rslt = _calculate();
            repr = getOperandRepresentation(rslt);
            isError = false;
        } catch (NaNException e) {
            repr = getNaNRepresentation();
            isError = true;
        } catch (BadExpressionException e) {
            repr = getBadExpressionRepresentation();
            isError = true;
        }
        applyResult(repr, isError);
    }

    @SuppressWarnings("unchecked")
    protected void updateExpression() {
        final Collection<Object> copy = builder.readOnlyCopy();
        final StringBuilder sb = new StringBuilder();
        for (final Object obj : copy) {
            final String repr;
            if (obj instanceof Operator) {
                repr = getOperatorRepresentation((T) obj);
            } else if (obj instanceof Operand) {
                repr = getOperandRepresentation((S) obj);
            } else if (obj instanceof DecimalSymbol) {
                repr = getSymbolRepresentation((DecimalSymbol) obj);
            } else {
                throw new IllegalStateException("Unknown object type in builder copy");
            }
            sb.append(repr);
        }
        this.expr.setValue(sb.toString());
    }

    protected S _calculate() throws NaNException, BadExpressionException {
        final InfixExpression infxExpr = builder.build();
        final PostfixExpression postfxExpr = parser.parse(infxExpr);
        return calculatorr.calculate(postfxExpr);
    }

    protected void tryToCalculate() {
        String repr;
        try {
            final S rslt = _calculate();
            repr = getOperandRepresentation(rslt);
        } catch (NaNException | BadExpressionException e) {
            repr = "";
        }
        applyIntermediateResult(repr);
    }

    protected void applyIntermediateResult(String value) {
        this.rslt.setValue(value);
    }

    protected void applyResult(String value, boolean isError) {
        this.expr.setValue(value);
        this.isError.setValue(isError);
    }

    protected String getNaNRepresentation() {
        return "Not a number";
    }

    protected String getBadExpressionRepresentation() {
        return "Bad expression";
    }

    protected String getSymbolRepresentation(DecimalSymbol symb) {
        return Character.toString(symb.getValue());
    }

    protected abstract String getOperatorRepresentation(T oprtr);

    protected abstract String getOperandRepresentation(S oprnd);
}
