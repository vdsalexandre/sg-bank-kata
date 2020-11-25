package kata.sg.model;

import kata.sg.exception.WrongAmountException;

import java.math.BigDecimal;

public class Amount {
    private BigDecimal value;

    private Amount(String initialAmount) throws WrongAmountException {
        try {
            value = new BigDecimal(initialAmount);
        } catch (NumberFormatException ex) {
            throw new WrongAmountException("Wrong amount, value needs to be a digit");
        }
    }

    public static Amount of(String initialAmount) throws WrongAmountException {
        return new Amount(initialAmount);
    }

    public BigDecimal getValue() {
        return value;
    }

    public Amount negate() throws WrongAmountException {
        return new Amount(value.negate().toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount = (Amount) o;
        return value.equals(amount.value);
    }

    public Amount add(Amount other) throws WrongAmountException {
        return new Amount(value.add(other.value).toString());
    }

    public boolean isGreaterThan(Amount other) {
        return value.compareTo(other.value) > 0;
    }
}