package kata.sg;

import kata.sg.exception.WrongAmountException;
import kata.sg.model.Amount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AmountTest {

    @Test
    void returns_true_when_new_amount_without_value_is_equal_to_ZERO() throws WrongAmountException {
        BigDecimal expectedAmount = BigDecimal.ZERO;

        Amount amount = Amount.of("0");

        assertThat(amount.getValue()).isEqualTo(expectedAmount);
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1500.50", "-1", "0", "1", "2", "12.50", "1627.45"})
    void returns_true_when_new_amount_value_is_equal_to_expected_value(String initialAmount) throws WrongAmountException {
        BigDecimal expectedAmount = new BigDecimal(initialAmount);

        Amount amount = Amount.of(initialAmount);

        assertThat(amount.getValue()).isEqualTo(expectedAmount);
    }

    @ParameterizedTest
    @ValueSource(strings = {"toto", "125GT", "-0.4E", "???"})
    void throws_exception_when_wrong_amount_is_created(String initialAmount) {
        assertThatThrownBy(() -> Amount.of(initialAmount))
                .isInstanceOf(WrongAmountException.class)
                .hasMessage("Wrong amount, value needs to be a digit");
    }

    @Test
    void returns_true_when_negate_value_is_equal_to_expected_value() throws WrongAmountException {
        Amount expectedAmount = Amount.of("-5");

        Amount amount = Amount.of("5");
        Amount negateAmount = amount.negate();

        assertThat(negateAmount).isEqualTo(expectedAmount);
    }

    @ParameterizedTest
    @CsvSource({"50,100", "99.99,100"})
    void returns_true_when_amount_is_greater_than_expected_amount() throws WrongAmountException {
        Amount smallAmount = Amount.of("50");
        Amount amount = Amount.of("100");

        assertThat(amount.isGreaterThan(smallAmount)).isTrue();
    }
}
