package kata.sg;

import kata.sg.exception.WrongAmountException;
import kata.sg.model.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class SaveMoneyTest {

    // US 1:
    //
    // In order to save money
    // As a bank client
    // I want to make a deposit in my account


    @Test
    @DisplayName("Test 1: empty account returns a balance of ZERO")
    void returns_true_when_empty_account_balance_is_equal_to_ZERO() {
        Account account = new Account();

        assertThat(account.getBalance()).isEqualTo(BigDecimal.ZERO);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "2", "3.50", "100", "1750650", "13.88"})
    @DisplayName("Test 2: account balance returns the good amount deposited")
    void returns_true_when_account_balance_is_equal_to_amount_deposited(String amount) throws WrongAmountException {
        BigDecimal expectedBalance = new BigDecimal(amount);

        Account account = new Account();
        account.deposit(new BigDecimal(amount));

        assertThat(account.getBalance()).isEqualTo(expectedBalance);
    }

    @ParameterizedTest
    @CsvSource({"12.25,45.34,19.22,76.81",
                "3686.44,123.99,562624.18,566434.61",
                "1,2,3,6"})
    @DisplayName("Test 3: account balance returns the good amount after some deposits")
    void returns_true_when_account_balance_is_equal_to_the_sum_of_all_deposits(String firstAmount, String secondAmount, String thirdAmount, String result) throws WrongAmountException {
        BigDecimal expectedBalance = new BigDecimal(result);

        Account account = new Account();
        account.deposit(new BigDecimal(firstAmount));
        account.deposit(new BigDecimal(secondAmount));
        account.deposit(new BigDecimal(thirdAmount));

        assertThat(account.getBalance()).isEqualTo(expectedBalance);
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "0", "-2500.25", "-0.01"})
    @DisplayName("Test 4: an exception is thrown when a wrong amount is deposit")
    void throws_wrong_amount_exception_when_trying_to_deposit_wrong_amount(String amount) {
        Account account = new Account();
        assertThatThrownBy(() -> account.deposit(new BigDecimal(amount)))
                .isInstanceOf(WrongAmountException.class)
                .hasMessage("Wrong amount, value needs to be greater than 0");
    }
}
