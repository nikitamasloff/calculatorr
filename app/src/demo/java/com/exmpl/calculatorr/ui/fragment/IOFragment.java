package com.exmpl.calculatorr.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.exmpl.calculatorr.R;
import com.exmpl.calculatorr.data.model.oprnd.Operand;
import com.exmpl.calculatorr.data.model.oprtr.Operator;
import com.exmpl.calculatorr.ui.viewmodel.MainViewModel;

public class IOFragment extends CalculatorrFragment<Operator, Operand, MainViewModel> {

    private TextView expr;
    private TextView rslt;

    public IOFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_io, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.expr = view.findViewById(R.id.tv_expr);
        this.rslt = view.findViewById(R.id.tv_rslt);

        final MainViewModel viewModel = getViewModel();
        viewModel.getExpression().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                expr.setText(s);
            }
        });
        viewModel.getResult().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                rslt.setText(s);
            }
        });
    }
}
