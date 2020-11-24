package kata.sg;

import kata.sg.model.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class SaveMoneyTest {

    @Test
    void test_environment() {
        assertThat(true).isTrue();
    }

    // US 1:
    //
    // In order to save money
    // As a bank client
    // I want to make a deposit in my account

    @ParameterizedTest
    @ValueSource(strings = {"0", "1", "3.50", "100", "1750650", "13.88"})
    void returns_true_when_account_balance_is_equal_to_amount_deposited(String amount) {
        BigDecimal expectedBalance = new BigDecimal(amount);

        Account account = new Account();
        account.deposit(new BigDecimal(amount));

        assertThat(account.getBalance()).isEqualTo(expectedBalance);
    }
}
