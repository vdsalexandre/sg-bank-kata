package kata.sg.model;

import kata.sg.exception.WrongAmountException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private List<Transaction> transactions = new ArrayList<>();

    public Account() { }

    public Account(Amount initialAmount) {
        if (initialAmount != null)
            deposit(initialAmount);
    }

    public void deposit(Amount amount) {
        addTransaction(amount);
    }

    public Amount getBalance() {
        Amount balance = Amount.of(BigDecimal.ZERO);

        for (Transaction transaction : transactions) {
            balance = balance.add(transaction.getAmount());
        }

        return balance;
    }

    public void withdraw(Amount amount) throws WrongAmountException {
        if (amount.isGreaterThan(getBalance()))
            throw new WrongAmountException("Wrong amount, the amount withdrawn must not be greater than the balance");

        addTransaction(amount.negate());
    }

    public List<Transaction> getTransactionHistory() {
        return transactions;
    }

    private void addTransaction(Amount amount) {
        transactions.add(new Transaction(LocalDateTime.now(), amount));
    }
}
