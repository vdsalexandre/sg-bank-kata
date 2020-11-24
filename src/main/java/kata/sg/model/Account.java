package kata.sg.model;

import kata.sg.exception.WrongAmountException;

import java.math.BigDecimal;

public class Account {

    private BigDecimal balance;

    public Account() {
        balance = BigDecimal.ZERO;
    }

    public Account(BigDecimal amount) {
        if (amount == null)
            balance = BigDecimal.ZERO;
        else
            balance = amount;
    }

    public void deposit(BigDecimal amount) throws WrongAmountException {
        if (amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new WrongAmountException("Wrong amount, value needs to be greater than 0");

        balance = balance.add(amount);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void withdraw(BigDecimal amount) {
        balance = balance.subtract(amount);
    }
}
