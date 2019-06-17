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
        System.out.println("Memory = (result = " + this.memory.getResult() + ")");
    }

    public Memory getMemory() {
        return this.memory;
    }

    public void clearMemory() {
        this.memory = null;
    }

    public void clear() {
        this.operatorStack.clear();
        this.numberStack.clear();
    }

    public double eval() {
        this.lastCalculation = new Memory(this.numberStack, this.operatorStack);
        System.out.println("numberStack == " + Arrays.toString(this.numberStack.toArray()));
        System.out.println("operatorStack == " + Arrays.toString(this.operatorStack.toArray()));
        double result = 0;
        while (!numberStack.isEmpty()) {
            if (numberStack.size() == 1) {
                result = numberStack.pop();
                break;
            }

            Double num1 = numberStack.pop();
            Double num2 = numberStack.pop();

            System.out.println("num1 == " + num1);
            System.out.println("num2 == " + num2);

            if (operatorStack.isEmpty()) {
                return num1;
            }
            Operator operator = operatorStack.pop();

            double temp = 0;
            if (operator == Operator.ADD) {
                temp = num2 + num1;
            } else if (operator == Operator.SUB) {
                temp = num2 - num1;
            } else if (operator == Operator.MULT) {
                temp = num2 * num1;
            } else if (operator == Operator.DIV) {
                temp = num2 / num1;
            } else if (operator == Operator.SQUARE) {
                temp = Math.sqrt(num1);
            }
            System.out.println("temp == " + temp);
            numberStack.push(temp);
        }
        this.lastCalculation.setResult(result);
        return result;
    }
}
