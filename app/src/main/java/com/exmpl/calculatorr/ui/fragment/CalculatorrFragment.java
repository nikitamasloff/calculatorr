package com.exmpl.calculatorr.ui.fragment;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.exmpl.calculatorr.data.model.oprnd.Operand;
import com.exmpl.calculatorr.data.model.oprtr.Operator;
import com.exmpl.calculatorr.ui.activity.CalculatorrActivity;
import com.exmpl.calculatorr.ui.viewmodel.CalculatorrViewModel;

import java.util.Objects;

public abstract class CalculatorrFragment<
        T extends Operator,
        S extends Operand,
        U extends CalculatorrViewModel<T, S>> extends Fragment {

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (!(context instanceof CalculatorrActivity<?, ?, ?>)) {
            throw new IllegalStateException("Only CalculatorrActivity can be parent of this fragment");
        }
    }

    @SuppressWarnings("unchecked")
    protected U getViewModel() {
        final CalculatorrActivity<T, S, U> calculatorrActivity =
                (CalculatorrActivity<T, S, U>) Objects.requireNonNull(getActivity());
        return calculatorrActivity.getViewModel();
    }
}
