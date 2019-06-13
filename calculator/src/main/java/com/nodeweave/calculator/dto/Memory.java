package com.nodeweave.calculator.dto;

import java.util.Stack;

public class Memory {
    private double result;
    private final Stack<Double> numberStack;
    private final Stack<Operator> operatorStack;

    public Memory(Stack<Double> numberStack, Stack<Operator> operatorStack) {
        this.numberStack = numberStack;
        this.operatorStack = operatorStack;
    }

    public Stack<Double> getNumberStack() {
        return numberStack;
    }

    public Stack<Operator> getOperatorStack() {
        return operatorStack;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
