package kata.sg.model;

import java.math.BigDecimal;

public class Account {

    private BigDecimal balance;

    public void deposit(BigDecimal amount) {
        balance = amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
