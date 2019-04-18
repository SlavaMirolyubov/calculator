package src;

import src.MyExceptions.BadSymbolException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Validator {

    boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    String validate(String expression) {

        expression = expression
                .replaceAll(" ", "")
                .replaceAll(",", ".")
                .replaceAll("\\(-", "(0-")
                .replaceAll("\\(\\+", "(0+");

        if (expression.charAt(0) == '-') {
            expression = "0" + expression;
        }

        Matcher matcherForNotNumbers = Pattern.compile("[^(.)/*\\-+0-9]").matcher(expression);
        Matcher matcherForRepeatOps = Pattern.compile("[./*\\-+]{2,}").matcher(expression);
        Matcher matcherForBrackets = Pattern.compile("[(][/*][(]|[)][/*][)]").matcher(expression);
        Matcher matcherForNumNearBrackets = Pattern.compile("[\\d?.]+[(]|[)][\\d?.]+").matcher(expression);

        boolean findBadSymbols = matcherForNotNumbers.find();
        boolean findBadSymbols2 = matcherForRepeatOps.find();
        boolean findBadSymbols3 = matcherForBrackets.find();
        boolean findBadSymbols4 = matcherForNumNearBrackets.find();

        if (findBadSymbols | findBadSymbols2 | findBadSymbols3 | findBadSymbols4) {
            throw new BadSymbolException("Недопустимая строка");
        }

        return expression;

    }

}
