package kata.sg.model;

import java.math.BigDecimal;

public class Account {

    private BigDecimal balance;

    public Account() {
        balance = BigDecimal.ZERO;
    }

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
