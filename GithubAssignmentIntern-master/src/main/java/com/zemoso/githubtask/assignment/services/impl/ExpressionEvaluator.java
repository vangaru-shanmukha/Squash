package com.zemoso.githubtask.assignment.services.impl;

import java.util.Stack;

public class ExpressionEvaluator {

	public boolean isOperator(char character) {
		if(character == '+' || character == '-' || character == '*' || character == '/' || character == '(') {
			return true;
		}
		return false;
	}

	/**
	 * This method is used to get the result of the input expression
	 * 
	 * @param expression
	 * @return result
	 * @throws UnsupportedOperationException
	 *             when it encounters divide by zero exception
	 */
	public String getResult(String expression) {
		expression = expression.replaceAll("\\s*", "");
		String result = null;
		Stack<Double> values = new Stack<Double>();
		Stack<Character> operators = new Stack<Character>();
		boolean isValid = true;
		for (int pos = 0; pos < expression.length();) {
			boolean flag = false;
			char character = expression.charAt(pos);
			if (character >= '0' && character <= '9') {
				String number = "";
				while (pos < expression.length() && ((expression.charAt(pos) >= '0' && expression.charAt(pos) <= '9')
						|| expression.charAt(pos) == '.')) {
					number = number + expression.charAt(pos);
					pos = pos + 1;
					flag = true;
				}
				values.push(Double.parseDouble(number));
			} else if (character == '+' || character == '-' || character == '/' || character == '*') {
				if (pos == 0) {
					pos = pos + 1;
					String number = character + "";
					while (pos < expression.length()
							&& ((expression.charAt(pos) >= '0' && expression.charAt(pos) <= '9')
									|| expression.charAt(pos) == '.')) {
						number = number + expression.charAt(pos);
						pos = pos + 1;
						flag = true;
					}
					values.push(Double.parseDouble(number));
					continue;
				} else if (isOperator(expression.charAt(pos - 1)) && pos > 0) {
					pos = pos + 1;
					String number = character + "";
					while (pos < expression.length()
							&& ((expression.charAt(pos) >= '0' && expression.charAt(pos) <= '9')
									|| expression.charAt(pos) == '.')) {
						number = number + expression.charAt(pos);
						pos = pos + 1;
						flag = true;
					}
					values.push(Double.parseDouble(number));
					continue;
				}
				while ((operators.isEmpty() == false)
						&& getPrecedenceValue(operators.peek()) >= getPrecedenceValue(character)) {
					char operator = operators.pop();
					Double number1 = values.pop();
					Double number2 = values.pop();
					Double value = calculate(operator, number2, number1);
					values.push(value);
				}
				operators.push(character);
			} else if (character == ')' || character == '(') {
				if (character == '(') {
					operators.push(character);
				} else {
					char operator = operators.peek();
					while (!operators.isEmpty() && operator != '(') {
						operator = operators.pop();
						Double number1 = values.pop();
						Double number2 = values.pop();
						Double value = calculate(operator, number2, number1);
						values.push(value);
						operator = operators.peek();
					}
					operators.pop();
				}
			}
			else if(getPrecedenceValue(character) == -1)
			{
				result = "-1";
				isValid = false;
				break;
			}
			if (flag == false)
				pos = pos + 1;
		}
		while (!operators.isEmpty() && isValid) {
			char operator = operators.pop();
			Double number1 = values.pop();
			Double number2 = values.pop();
			Double value = calculate(operator, number2, number1);
			values.push(value);
		}
		if(isValid)
			result = values.pop().toString();
		return result;
	}

	/**
	 * This method is used to calculate the result of subexpression with one
	 * operator and two numbers
	 * 
	 * @param operator
	 * @param number1
	 * @param number2
	 * @return
	 * @throws UnsupportedOperationException
	 *             if the user enters an invalid operator
	 */
	private Double calculate(char operator, Double number1, Double number2) {
		switch (operator) {
		case ('+'):
			return number1 + number2;
		case ('-'):
			return number1 - number2;
		case ('/'):
			if (number2 == 0) {
				return -1.0;
			}
			return number1 / number2;
		case ('*'):
			return number1 * number2;
		default:
			return -1.0;
		}
	}

	/**
	 * This method is used to get the precedence of the operator
	 * 
	 * @param operator
	 * @return p
	 */
	private int getPrecedenceValue(Character operator) {
		switch (operator) {
		case ('+'):
			return 1;
		case ('-'):
			return 1;
		case ('/'):
			return 2;
		case ('*'):
			return 2;
		case ('('):
			return 0;
		case (')'):
			return 0;
		default:
			return -1;
		}
	}
}
