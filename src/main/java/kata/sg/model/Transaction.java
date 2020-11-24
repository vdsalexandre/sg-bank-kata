package kata.sg.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {

    private final LocalDateTime transactionDateTime;
    private final Operation operation;
    private final BigDecimal amount;

    public Transaction(LocalDateTime transactionDateTime, Operation operation, BigDecimal amount) {
        this.transactionDateTime = transactionDateTime.withNano(0);
        this.operation = operation;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return transactionDateTime.equals(that.transactionDateTime) &&
                operation == that.operation &&
                amount.equals(that.amount);
    }
}
