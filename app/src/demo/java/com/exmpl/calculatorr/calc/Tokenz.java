package com.exmpl.calculatorr.calc;

import static com.exmpl.calculatorr.util.Utilz.isIn;

public final class Tokenz {

    private Tokenz() {
    }

    private static final Character[] TOKENZ = {'+', '-', '×', '÷',
            '.', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '∞'};

    public static Character[] allTokens() {
        return TOKENZ.clone();
    }

    public static boolean isToken(char ch) {
        return isIn(TOKENZ, ch);
    }
}
