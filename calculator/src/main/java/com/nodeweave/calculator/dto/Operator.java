package com.nodeweave.calculator.dto;

public enum Operator {
    DIV,
    ADD,
    SUB,
    MULT,
    SQUARE;

    public static Operator findByCode(char c) {
        switch (c) {
            case '/':
                return DIV;
            case '+':
                return ADD;
            case '-':
                return SUB;
            case '*':
                return MULT;
            case '√':
                return SQUARE;
        }
        return null;
    }
}
