package kata.sg.model;

import java.time.LocalDateTime;

public class Transaction {

    private final LocalDateTime transactionDateTime;
    private final Amount amount;

    public Transaction(LocalDateTime transactionDateTime, Amount amount) {
        this.transactionDateTime = transactionDateTime.withNano(0);
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return transactionDateTime.equals(that.transactionDateTime) &&
                amount.equals(that.amount);
    }

    public Amount getAmount() {
        return amount;
    }
}
