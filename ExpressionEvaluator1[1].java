package dsa;
import java.util.*;

public class ExpressionEvaluator1 {

    static class Node {
        String val;
        Node left, right;

        Node(String val) {
            this.val = val;
        }
    }

    public int evaluate(String s) {
        List<String> tokens = tokenize(s);
        Node root = buildExpressionTree(tokens);
        return evaluateTree(root);
    }

    private List<String> tokenize(String s) {
        List<String> tokens = new ArrayList<>();
        int n = s.length();
        StringBuilder num = new StringBuilder();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (c == ' ') continue;

            if (Character.isDigit(c)) {
                num.append(c);
            } else {
                if (num.length() > 0) {
                    tokens.add(num.toString());
                    num.setLength(0);
                }
                tokens.add(String.valueOf(c));
            }
        }

        if (num.length() > 0) {
            tokens.add(num.toString());
        }

        return tokens;
    }

    private Node buildExpressionTree(List<String> tokens) {
        Stack<Node> operandStack = new Stack<>();
        Stack<String> operatorStack = new Stack<>();

        for (String token : tokens) {
            if (Character.isDigit(token.charAt(0))) {
                operandStack.push(new Node(token)); 
            } else if (token.equals("(")) {
                operatorStack.push(token); 
            } else if (token.equals(")")) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    processOperator(operandStack, operatorStack.pop());
                }
                operatorStack.pop(); 
            } else if (isOperator(token)) {
                while (!operatorStack.isEmpty() && hasHigherPrecedence(operatorStack.peek(), token)) {
                    processOperator(operandStack, operatorStack.pop());
                }
                operatorStack.push(token);
            }
        }

        while (!operatorStack.isEmpty()) {
            processOperator(operandStack, operatorStack.pop());
        }

        return operandStack.pop();
    }

    private void processOperator(Stack<Node> operandStack, String operator) {
        Node right = operandStack.pop();
        Node left = operandStack.pop();
        Node node = new Node(operator);
        node.left = left;
        node.right = right;
        operandStack.push(node);
    }

    private boolean hasHigherPrecedence(String operator1, String operator2) {
        return getPriority(operator1) >= getPriority(operator2);
    }

    private int getPriority(String operator) {
        if (operator.equals("+") || operator.equals("-")) {
            return 1;
        } else if (operator.equals("*") || operator.equals("/")) {
            return 2;
        }
        return 0;
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private int evaluateTree(Node root) {
        if (root == null) return 0;

        if (!isOperator(root.val)) {
            return Integer.parseInt(root.val);
        }

        int leftVal = evaluateTree(root.left);
        int rightVal = evaluateTree(root.right);

        switch (root.val) {
            case "+": return leftVal + rightVal;
            case "-": return leftVal - rightVal;
            case "*": return leftVal * rightVal;
            case "/": return leftVal / rightVal;
            default: return 0;
        }
    }

    public static void main(String[] args) {
        ExpressionEvaluator1 evaluator = new ExpressionEvaluator1();
        
        String expression = "3+2*2";
        System.out.println("Result: " + evaluator.evaluate(expression));
    }
}
