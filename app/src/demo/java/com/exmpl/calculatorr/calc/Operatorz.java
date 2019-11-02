package com.exmpl.calculatorr.calc;

import static com.exmpl.calculatorr.util.Utilz.isIn;

public final class Operatorz {

    private Operatorz() {
    }

    private static final Character[] OPERATORZ = {'+', '-', '×', '÷'};

    public static Character[] allOperators() {
        return OPERATORZ.clone();
    }

    public static boolean isOperator(String src) {
        return src != null && src.length() == 1 && isIn(OPERATORZ, src.charAt(0));
    }
}