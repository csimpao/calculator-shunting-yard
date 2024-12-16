import java.util.*;

public class Tokenizer {
    Stack<String> postfixStack;
    Stack<String> opStack;
    Map<String, Double> op;
    public Tokenizer() {
        postfixStack = new Stack<>();
        opStack = new Stack<>();
        op = new HashMap<String, Double>();
    }

    public Stack<String> makePostfix(String expression) {
        String num = "";
        String constant = "";
        for (int i = 0; i < expression.length(); i++) {
            char token = expression.charAt(i);
            if (Character.isDigit(token)) {
                num += Character.toString(token);
                if ((i+1 < expression.length()) && Character.isDigit(expression.charAt(i+1))) {

                } else {
                    postfixStack.push(num);
                    num = "";
                }
            } else if (Character.isLetter(token)) {
                constant += Character.toString(token);
                if ((i+1 < expression.length()) && Character.isLetter(expression.charAt(i+1))) {

                } else if (constant.equals("pi")) {
                    // Captures the pi constant
                    postfixStack.push("3.14");
                    constant = "";
                } else if (constant.equals("e")) {
                    // Captures euler's constant e
                    postfixStack.push("2.71828");
                    constant = "";
                }
            } else if (token == '(') {
                opStack.push(Character.toString(token));
            } else if (token == ')') {
                while (!"(".equals(opStack.peek())) {
                    postfixStack.push(opStack.pop());
                }
                opStack.pop();
            } else {
                while (!opStack.isEmpty() && (opPrecedence(Character.toString(token)) <= opPrecedence(opStack.peek()))) {
                    postfixStack.push(opStack.pop());
                }
                opStack.push(Character.toString(token));
            }
        }
        while (!opStack.isEmpty()) {
            postfixStack.push(opStack.pop());
        }
        return postfixStack;
    }

    public static int opPrecedence(String op) {
        if ("^".equals(op)) {
            return 3;
        } else if ("*".equals(op) || "/".equals(op)) {
            return 2;
        } else if ("+".equals(op) || "-".equals(op)) {
            return 1;
        }
        return -1;
    }

    public double evaluate(Stack<String> postfix) {
        Stack<String> tempStack = new Stack<>();
        System.out.println("\nTokenizer Stack Processing: ");
        for (int i = 0; i < postfix.size(); i++) {
            String token = postfix.get(i);
            if (isNum(token)){
                tempStack.push(token);
            } else {
                double b = Double.parseDouble(tempStack.pop());
                double a = Double.parseDouble(tempStack.pop());
                op.put("+", a + b);
                op.put("-", a - b);
                op.put("*", a * b);
                op.put("/", a / b);
                op.put("^", Math.pow(a, b));
                tempStack.push(Double.toString(op.get(token)));
            }
            System.out.println(tempStack);
        }
        return Double.parseDouble(tempStack.pop());
    }

    public static boolean isNum(String token) {
        try {
            Double.valueOf(token);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Arithmetic Operator");
        }
        return false;
    }
}