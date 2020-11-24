package kata.sg;

import kata.sg.model.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class WithDrawalMoneyTest {

    // US 2:
    // In order to retrieve some or all of my savings
    // As a bank client
    // I want to make a withdrawal from my account


    @ParameterizedTest
    @CsvSource({"100,1,99",
                "175.95,175.95,0.00",
                "10,7.50,2.50",
                "15,15,0"})
    @DisplayName("Test 1: after a withdraw, the account balance is correct")
    void returns_true_when_account_balance_is_equal_to_expected_balance_after_withdraw(String initialAmount, String amount, String expectedBalance) {
        Account account = new Account(new BigDecimal(initialAmount));
        account.withdraw(new BigDecimal(amount));
        assertThat(account.getBalance()).isEqualTo(expectedBalance);
    }
}
