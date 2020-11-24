package kata.sg.model;

import kata.sg.exception.WrongAmountException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static kata.sg.model.Operation.DEPOSIT;

public class Account {

    private BigDecimal balance;
    private List<Transaction> transactions = new ArrayList<>();

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
        transactions.add(new Transaction(LocalDateTime.now(), DEPOSIT, amount));
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void withdraw(BigDecimal amount) throws WrongAmountException {
        if (amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new WrongAmountException("Wrong amount, value needs to be greater than 0");

        if (amount.compareTo(balance) > 0)
            throw new WrongAmountException("Wrong amount, the amount to withdraw is greater than the balance");

        balance = balance.subtract(amount);
    }

    public List<Transaction> getTransactionHistory() {
        return transactions;
    }
}
