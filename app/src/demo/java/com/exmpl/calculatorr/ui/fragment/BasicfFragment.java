package com.exmpl.calculatorr.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.exmpl.calculatorr.R;
import com.exmpl.calculatorr.data.model.oprnd.Operand;
import com.exmpl.calculatorr.data.model.oprtr.DivideOperator;
import com.exmpl.calculatorr.data.model.oprtr.MinusOperator;
import com.exmpl.calculatorr.data.model.oprtr.MultiplyOperator;
import com.exmpl.calculatorr.data.model.oprtr.Operator;
import com.exmpl.calculatorr.data.model.oprtr.PlusOperator;
import com.exmpl.calculatorr.ui.viewmodel.MainViewModel;

public class BasicfFragment extends CalculatorrFragment<Operator, Operand, MainViewModel> {

    public BasicfFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_basicf, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        view.findViewById(R.id.tv_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getViewModel().detach();
            }
        });
        view.findViewById(R.id.tv_divide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getViewModel().attach(new DivideOperator());
            }
        });
        view.findViewById(R.id.tv_multiply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getViewModel().attach(new MultiplyOperator());
            }
        });
        view.findViewById(R.id.tv_minus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getViewModel().attach(new MinusOperator());
            }
        });
        view.findViewById(R.id.tv_plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getViewModel().attach(new PlusOperator());
            }
        });
    }
}
