package com.zemoso.githubtask.assignment.services.impl;

import com.zemoso.githubtask.assignment.services.CalculationService;

import org.springframework.stereotype.Service;

@Service
public class PostfixCalculationService implements CalculationService {

    @Override
    public int calculateInt(String input) {
    	int result = 0;
    	try {
    		result = Math.toIntExact((long)(Double.parseDouble(new ExpressionEvaluator().getResult(input))));
    	}
    	catch(ArithmeticException e) {
    		result = -1;
    	}
        return result;
    }

    @Override
    public float calculateFloat(String input) {
        return (float)(Double.parseDouble(new ExpressionEvaluator().getResult(input)));
    }

    @Override
    public long calculateLong(String input) {
        return (long)(Double.parseDouble(new ExpressionEvaluator().getResult(input)));
    }

    @Override
    public double calculateDouble(String input) {
    	return Double.parseDouble(new ExpressionEvaluator().getResult(input));    
    }
}
