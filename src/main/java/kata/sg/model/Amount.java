package kata.sg.model;

import java.math.BigDecimal;

public class Amount {
    private BigDecimal value;

    private Amount(BigDecimal initialAmount) {
        if (initialAmount != null)
            value = initialAmount;
        else
            value = BigDecimal.ZERO;
    }

    public static Amount of(BigDecimal initialAmount) {
        return new Amount(initialAmount);
    }

    public BigDecimal getValue() {
        return value;
    }

    public Amount negate() {
        return new Amount(value.negate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount amount = (Amount) o;
        return value.equals(amount.value);
    }

    public Amount add(Amount other) {
        return new Amount(value.add(other.value));
    }

    public boolean isGreaterThan(Amount other) {
        return value.compareTo(other.value) > 0;
    }
}
