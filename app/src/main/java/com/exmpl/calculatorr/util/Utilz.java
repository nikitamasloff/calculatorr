package com.exmpl.calculatorr.util;

public final class Utilz {

    private Utilz() {
    }

    public static <T> boolean isIn(T[] array, T value) {
        if (array == null)
            return false;
        for (final T item : array) {
            if ((item == null && value == null)
                    || (item != null && item.equals(value)))
                return true;
        }
        return false;
    }

    public static boolean isIn(String src, char ch) {
        return src.lastIndexOf(ch) != -1;
    }

    public static boolean isIn(StringBuilder src, char ch) {
        return src.lastIndexOf(Character.toString(ch)) != -1;
    }

    public static boolean isInAnyOf(String src, String chars) {
        final int len = chars.length();
        for (int i = 0; i < len; ++i) {
            if (isIn(src, chars.charAt(i)))
                return true;
        }
        return false;
    }

    public static boolean isInAnyOf(StringBuilder src, String chars) {
        final int len = chars.length();
        for (int i = 0; i < len; ++i) {
            if (isIn(src, chars.charAt(i)))
                return true;
        }
        return false;
    }

    public static char lastIn(String src) {
        if (src == null || src.isEmpty())
            throw new IllegalArgumentException("Source is null or empty");
        return src.charAt(src.length() - 1);
    }

    public static char lastIn(StringBuilder src) {
        if (src == null || src.length() == 0)
            throw new IllegalArgumentException("Source is null or empty");
        return src.charAt(src.length() - 1);
    }

    public static char secondToLastIn(String src) {
        if (src == null || src.length() < 2)
            throw new IllegalArgumentException("Source is null or too short");
        return src.charAt(src.length() - 2);
    }

    public static char secondToLastIn(StringBuilder src) {
        if (src == null || src.length() < 2)
            throw new IllegalArgumentException("Source is null or too short");
        return src.charAt(src.length() - 2);
    }

    public static boolean isDigit(char ch) {
        return '0' == ch
                || '1' == ch
                || '2' == ch
                || '3' == ch
                || '4' == ch
                || '5' == ch
                || '6' == ch
                || '7' == ch
                || '8' == ch
                || '9' == ch;
    }

    public static boolean isDigitOrPoint(char ch) {
        return isDigit(ch) || ch == '.';
    }

    public static boolean isDigitsOnly(String src) {
        if (src == null || src.isEmpty())
            return false;
        final int len = src.length();
        for (int i = 0; i < len; ++i) {
            if (!isDigit(src.charAt(i)))
                return false;
        }
        return true;
    }
}
