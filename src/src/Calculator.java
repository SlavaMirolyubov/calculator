package src;

import src.MyExceptions.BracketsException;
import src.MyExceptions.DivideByZeroException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Calculator {

    private ArrayList<String> inputArray = new ArrayList<>();
    private Stack<String> operators = new Stack<>();
    private Stack<String> outStroke = new Stack<>();

    private Priority priority = new Priority();

    private Validator validator = new Validator();

    void calculate(String expression) {

        expression = validator.validate(expression);

        System.out.println("Выражение : " + expression);

        String resultString = null;
        
        fillInputArray(expression);

        fillRPNStack(inputArray);

        resultString = countFromRPN().toString();


        System.out.println(resultString);

    }

    private void fillInputArray(String expression) {

        Pattern pattern = Pattern.compile("[\\d?.]+|[)(+*\\-/]");
        Matcher matcher = pattern.matcher(expression);

        while (matcher.find()) {
            inputArray.add(matcher.group());
        }

    }

    private void fillRPNStack(ArrayList<String> list) {

        for (String elem : list) {

            if (elem.matches("[\\d?.]+")) {
                outStroke.push(elem);
            } else if (elem.equals("(")) {
                operators.push(elem);
            } else if (elem.equals(")")) {
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    outStroke.push(operators.pop());
                }

                if (!operators.isEmpty() && operators.peek().equals("(")) {
                    operators.pop();
                } else {
                    throw new BracketsException("Несогласованность скобок");
                }
            } else if (elem.matches("[/*\\-+]")) {

                if (!operators.isEmpty() && (priority.getPriority(elem) <= priority.getPriority(operators.peek()))) {
                    while (!operators.isEmpty() && (priority.getPriority(operators.peek()) >= priority.getPriority(elem))) {
                        outStroke.push(operators.pop());
                    }
                }
                operators.push(elem);

            }
        }

        if (!operators.isEmpty()) {
            while (!operators.isEmpty()) {

                if (operators.peek().equals("(")) {
                    throw new BracketsException("Несогласованность скобок");
                }
                outStroke.push(operators.pop());

            }
        }
    }

    private BigDecimal countFromRPN() throws DivideByZeroException {

        Stack<BigDecimal> result = new Stack<>();

        Collections.reverse(outStroke);

        while(!outStroke.isEmpty()) {
            String elem = outStroke.pop();
            if (validator.isNumber(elem)){
                result.push(new BigDecimal(elem));
            } else {
                BigDecimal tmp = result.pop();
                result.push(Operations.getEnum(elem).apply(result.pop(), tmp));
            }
        }

        return result.pop();

    }



}
