package com.nodeweave.calculator.dto;


import java.util.Arrays;
import java.util.Stack;

public class Calculator {
    private Stack<Double> numberStack;
    private Stack<Operator> operatorStack;
    private Memory memory;
    private Memory lastCalculation;

    public Calculator() {
        this.numberStack = new Stack<>();
        this.operatorStack = new Stack<>();
    }

    public void addNumber(double number) {
        this.numberStack.push(number);
    }

    public void addOperator(Operator operator) {
        this.operatorStack.add(operator);
    }

    public void storeMemory() {
        this.memory = lastCalculation;
    }

    public Memory getMemory() {
        return this.memory;
    }

    public void clearMemory() {
        this.memory = null;
    }

    public double eval() {
        this.lastCalculation = new Memory(this.numberStack, this.operatorStack);
        double result = 0;
        while (!numberStack.isEmpty()) {
            if (numberStack.size() == 1) {
                result = numberStack.pop();
                break;
            }
            Operator operator = operatorStack.pop();
            Double num1 = numberStack.pop();
            Double num2 = numberStack.pop();

            double temp = 0;
            if (operator == Operator.ADD) {
                temp = num1 + num2;
            } else if (operator == Operator.SUB) {
                temp = num1 - num2;
            } else if (operator == Operator.MULT) {
                temp = num1 * num2;
            } else if (operator == Operator.DIV) {
                temp = num1 / num2;
            } else if (operator == Operator.SQUARE) {
                temp = Math.sqrt(num1);
            }
            numberStack.push(temp);
        }
        this.lastCalculation.setResult(result);
        return result;
    }
}
