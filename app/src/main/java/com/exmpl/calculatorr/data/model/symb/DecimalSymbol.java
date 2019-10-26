package com.exmpl.calculatorr.data.model.symb;

public abstract class DecimalSymbol extends Symbol {

    public DecimalSymbol(char value) {
        super(value);
    }

    public static class Factory extends Symbol.Factory<DecimalSymbol> {

        public Factory(){
        }

        @Override
        public DecimalSymbol fromChar(char ch) {
            switch (ch) {
                case '∞': return Infinity.getInstance();
                case '-': return Minus.getInstance();
                case '.': return Point.getInstance();
                case '0': return Zero.getInstance();
                case '1': return One.getInstance();
                case '2': return Two.getInstance();
                case '3': return Three.getInstance();
                case '4': return Four.getInstance();
                case '5': return Five.getInstance();
                case '6': return Six.getInstance();
                case '7': return Seven.getInstance();
                case '8': return Eight.getInstance();
                case '9': return Nine.getInstance();
                default: throw new IllegalArgumentException("Unknown decimal symbol");
            }
        }
    }
}
