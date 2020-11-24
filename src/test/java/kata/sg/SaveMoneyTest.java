package kata.sg;

import kata.sg.model.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

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
    @ValueSource(strings = {"0", "1", "3.50", "100", "1750650", "13.88"})
    @DisplayName("Test 2: account balance returns the good amount deposited")
    void returns_true_when_account_balance_is_equal_to_amount_deposited(String amount) {
        BigDecimal expectedBalance = new BigDecimal(amount);

        Account account = new Account();
        account.deposit(new BigDecimal(amount));

        assertThat(account.getBalance()).isEqualTo(expectedBalance);
    }

    @ParameterizedTest
    @DisplayName("Test 3: account balance returns the good amount after some deposits")
    @CsvSource({"12.25,45.34,19.22,76.81",
                "3686.44,123.99,562624.18,566434.61",
                "1,2,3,6"})
    void returns_true_when_account_balance_is_equal_to_the_sum_of_all_deposits(String firstAmount, String secondAmount, String thirdAmount, String result) {
        BigDecimal expectedBalance = new BigDecimal(result);

        Account account = new Account();
        account.deposit(new BigDecimal(firstAmount));
        account.deposit(new BigDecimal(secondAmount));
        account.deposit(new BigDecimal(thirdAmount));

        assertThat(account.getBalance()).isEqualTo(expectedBalance);
    }
}
