package com.nodeweave.calculator.dao;

import com.nodeweave.calculator.dto.Calculator;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CalculatorDAO {
    private final Map<String, Calculator> calculatorMap;

    public CalculatorDAO() {
        this.calculatorMap = new ConcurrentHashMap<>();
    }

    public Calculator getCalculator(String session) {
        return this.calculatorMap.getOrDefault(session, new Calculator());
    }

    public void setCalculator(String session, Calculator calculator) {
        this.calculatorMap.put(session, calculator);
    }

    public void removeCalculator(String session) {
        this.calculatorMap.remove(session);
    }
}
