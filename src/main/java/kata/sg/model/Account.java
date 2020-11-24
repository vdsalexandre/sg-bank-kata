package kata.sg.model;

import kata.sg.exception.WrongAmountException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static kata.sg.model.Operation.DEPOSIT;
import static kata.sg.model.Operation.WITHDRAW;

public class Account {

    private BigDecimal balance = BigDecimal.ZERO;
    private List<Transaction> transactions = new ArrayList<>();

    public Account() { }

    public Account(BigDecimal amount) {
        if (amount != null) {
            balance = amount;
        }
    }

    public void deposit(BigDecimal amount) throws WrongAmountException {
        if (amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new WrongAmountException("Wrong amount, value needs to be greater than 0");

        balance = balance.add(amount);
        addTransaction(DEPOSIT, amount);
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
        addTransaction(WITHDRAW, amount);
    }

    public List<Transaction> getTransactionHistory() {
        return transactions;
    }

    private void addTransaction(Operation operation, BigDecimal amount) {
        transactions.add(new Transaction(LocalDateTime.now(), operation, amount));
    }
}
