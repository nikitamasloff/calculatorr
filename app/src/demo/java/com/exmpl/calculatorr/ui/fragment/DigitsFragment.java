package com.exmpl.calculatorr.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.exmpl.calculatorr.R;
import com.exmpl.calculatorr.data.model.oprnd.Operand;
import com.exmpl.calculatorr.data.model.oprtr.Operator;
import com.exmpl.calculatorr.data.model.symb.Eight;
import com.exmpl.calculatorr.data.model.symb.Five;
import com.exmpl.calculatorr.data.model.symb.Four;
import com.exmpl.calculatorr.data.model.symb.Nine;
import com.exmpl.calculatorr.data.model.symb.One;
import com.exmpl.calculatorr.data.model.symb.Point;
import com.exmpl.calculatorr.data.model.symb.Seven;
import com.exmpl.calculatorr.data.model.symb.Six;
import com.exmpl.calculatorr.data.model.symb.Three;
import com.exmpl.calculatorr.data.model.symb.Two;
import com.exmpl.calculatorr.data.model.symb.Zero;
import com.exmpl.calculatorr.ui.viewmodel.MainViewModel;

public class DigitsFragment extends CalculatorrFragment<Operator, Operand, MainViewModel> {

    private TextView zero;
    private TextView one;
    private TextView two;
    private TextView three;
    private TextView four;
    private TextView five;
    private TextView six;
    private TextView seven;
    private TextView eight;
    private TextView nine;
    private TextView point;
    private TextView equals;

    public DigitsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_digits, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        this.zero = view.findViewById(R.id.tv_0);
        this.one = view.findViewById(R.id.tv_1);
        this.two = view.findViewById(R.id.tv_2);
        this.three = view.findViewById(R.id.tv_3);
        this.four = view.findViewById(R.id.tv_4);
        this.five = view.findViewById(R.id.tv_5);
        this.six = view.findViewById(R.id.tv_6);
        this.seven = view.findViewById(R.id.tv_7);
        this.eight = view.findViewById(R.id.tv_8);
        this.nine = view.findViewById(R.id.tv_9);
        this.point = view.findViewById(R.id.tv_point);
        this.equals = view.findViewById(R.id.tv_equals);

        this.zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getViewModel().attach(Zero.getInstance());
            }
        });
        this.one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getViewModel().attach(One.getInstance());
            }
        });
        this.two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getViewModel().attach(Two.getInstance());
            }
        });
        this.three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getViewModel().attach(Three.getInstance());
            }
        });
        this.four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getViewModel().attach(Four.getInstance());
            }
        });
        this.five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getViewModel().attach(Five.getInstance());
            }
        });
        this.six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getViewModel().attach(Six.getInstance());
            }
        });
        this.seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getViewModel().attach(Seven.getInstance());
            }
        });
        this.eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getViewModel().attach(Eight.getInstance());
            }
        });
        this.nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getViewModel().attach(Nine.getInstance());
            }
        });
        this.point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getViewModel().attach(Point.getInstance());
            }
        });
        this.equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getViewModel().calculate();
            }
        });
    }
}
