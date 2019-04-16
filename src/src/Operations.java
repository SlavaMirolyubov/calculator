package src;

import src.MyExceptions.DivideByZeroException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum Operations {

    PLUS("+") {
        BigDecimal apply(BigDecimal x, BigDecimal y) {
            return x.add(y);
        }
    },
    MINUS("-") {
        BigDecimal apply(BigDecimal x, BigDecimal y) {
            return x.subtract(y);
        }
    },
    MULTIPLY("*") {
        BigDecimal apply(BigDecimal x, BigDecimal y) {
            return x.multiply(y);
        }
    },
    DIVIDE("/") {
        BigDecimal apply(BigDecimal x, BigDecimal y) throws DivideByZeroException {
            if (y.equals(new BigDecimal(0))) {
                throw new DivideByZeroException("Деление на ноль!");
            }
            return x.divide(y, 20, RoundingMode.HALF_UP);
        }
    };

    private final String symbol;

    Operations(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    abstract BigDecimal apply(BigDecimal x, BigDecimal y) throws DivideByZeroException;

    public static Operations getEnum(String value) {
        for (Operations operator : values())
            if (operator.getSymbol().equals(value)) return operator;
        throw new IllegalArgumentException();
    }
}
