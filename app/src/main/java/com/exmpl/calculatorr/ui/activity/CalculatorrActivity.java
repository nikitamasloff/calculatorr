package com.exmpl.calculatorr.ui.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.exmpl.calculatorr.data.model.oprnd.Operand;
import com.exmpl.calculatorr.data.model.oprtr.Operator;
import com.exmpl.calculatorr.ui.viewmodel.CalculatorrViewModel;

public abstract class CalculatorrActivity<
        T extends Operator,
        S extends Operand,
        U extends CalculatorrViewModel<T, S>> extends AppCompatActivity {

    private U viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.viewModel = createViewModel();
        render();
    }

    public U getViewModel() {
        return viewModel;
    }

    protected abstract U createViewModel();

    protected abstract void render();
}
