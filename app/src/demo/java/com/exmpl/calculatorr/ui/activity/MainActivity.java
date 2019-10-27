package com.exmpl.calculatorr.ui.activity;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.exmpl.calculatorr.R;
import com.exmpl.calculatorr.data.bldr.Builder;
import com.exmpl.calculatorr.data.bldr.BuilderImpl;
import com.exmpl.calculatorr.data.calc.Calculatorr;
import com.exmpl.calculatorr.data.calc.Parser;
import com.exmpl.calculatorr.data.calc.PostfixExprCalculatorr;
import com.exmpl.calculatorr.data.calc.ShuntingYardParser;
import com.exmpl.calculatorr.data.model.expr.InfixExpression;
import com.exmpl.calculatorr.data.model.expr.PostfixExpression;
import com.exmpl.calculatorr.data.model.oprnd.Operand;
import com.exmpl.calculatorr.data.model.oprtr.Operator;
import com.exmpl.calculatorr.data.model.symb.DecimalSymbol;
import com.exmpl.calculatorr.ui.fragment.BasicfFragment;
import com.exmpl.calculatorr.ui.fragment.DigitsFragment;
import com.exmpl.calculatorr.ui.fragment.IOFragment;
import com.exmpl.calculatorr.ui.viewmodel.MainViewModel;

public class MainActivity extends CalculatorrActivity<Operator, Operand, MainViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected MainViewModel createViewModel() {
        final DecimalSymbol.Factory symbolFactory = new DecimalSymbol.Factory();
        final Builder<Operator, DecimalSymbol, InfixExpression> builder = new BuilderImpl(symbolFactory);
        final Parser<InfixExpression, PostfixExpression> parser = new ShuntingYardParser();
        final Calculatorr<PostfixExpression, Operand> calculatorr = new PostfixExprCalculatorr();
        final MainViewModel.Factory viewModelFactory = new MainViewModel.Factory(builder, parser, calculatorr);
        return ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);
    }

    @Override
    protected void render() {
        final FragmentManager fm = getSupportFragmentManager();
        if (fm.findFragmentById(R.id.io_container) == null) {
            fm.beginTransaction()
                    .replace(R.id.io_container, new IOFragment())
                    .commit();
        }
        if (fm.findFragmentById(R.id.digits_container) == null) {
            fm.beginTransaction()
                    .replace(R.id.digits_container, new DigitsFragment())
                    .commit();
        }
        if (fm.findFragmentById(R.id.basicf_container) == null) {
            fm.beginTransaction()
                    .replace(R.id.basicf_container, new BasicfFragment())
                    .commit();
        }
    }
}
