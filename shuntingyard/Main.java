import java.text.DecimalFormat;
import java.util.*;
public class Main {
    public static void main(String[] args) {

        Stack<String> postfix = new Stack<>(); 
        double shuntingResult = 0.00d;
        String expressionInput;
        Tokenizer shunting = new Tokenizer();
        DecimalFormat df = new DecimalFormat("#.##");

        Scanner sc = new Scanner(System.in);
        System.out.print("Type in a mathematical expression: ");
        expressionInput = sc.nextLine();
        while (!expressionInput.equalsIgnoreCase("quit")) {
            postfix = shunting.makePostfix(expressionInput);
            shuntingResult = shunting.evaluate(postfix);
            System.out.println("Result of expression postfix: " + postfix);
            System.out.println("Answer to your epxression is: " + df.format(shuntingResult));
            System.out.println();
            System.out.print("Another expression or quit: ");
            postfix.clear();
            expressionInput = sc.nextLine();
        }
    }
}

