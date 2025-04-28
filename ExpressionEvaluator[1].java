package dsa;

import java.util.Stack;

public class ExpressionEvaluator {
    public int evaluate(String s) {

        Stack<Integer> stack = new Stack<>();
        
        int currentNumber = 0;
        char lastOperator = '+';
        
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            
            if (ch == ' ') continue;
            
            if (Character.isDigit(ch)) {
                currentNumber = currentNumber * 10 + (ch - '0');
            }
            
            if (!Character.isDigit(ch) || i == s.length() - 1) {
                if (lastOperator == '+') {
                    stack.push(currentNumber);
                } else if (lastOperator == '-') {
                    stack.push(-currentNumber);
                } else if (lastOperator == '*') {
                    stack.push(stack.pop() * currentNumber);
                } else if (lastOperator == '/') {
                    stack.push(stack.pop() / currentNumber);
                }
                
                lastOperator = ch;
                currentNumber = 0;
            }
        }
        
        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        
        return result;
    }

    public static void main(String[] args) {
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        String expression = "3+2*2";
        System.out.println("Result: " + evaluator.evaluate(expression));  
    }
}
/*
Advanced DSA Problem
Given a string s which represents an expression, evaluate this expression and return its value. 

The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].

Note: 
1- You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().
2- The string can have multiple operators. You are expected to comply with BODMAS rule.
3- You are expected to optimize the complexity- better than or equal to n log( n )
4- Do not use GenAI to answer this. You would be asked to explain how you came up with it.
*/