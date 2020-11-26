package kata.sg;

import kata.sg.exception.WrongAmountException;
import kata.sg.model.Account;
import kata.sg.model.Amount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WithdrawalMoneyTest {

    // US 2:
    // In order to retrieve some or all of my savings
    // As a bank client
    // I want to make a withdrawal from my account


    @ParameterizedTest
    @CsvSource({"100,1,99",
                "175.95,175.95,0.00",
                "15,15,0"})
    @DisplayName("Test 1: after a withdrawal, the account balance is correct")
    void returns_true_when_account_balance_is_equal_to_expected_balance_after_withdrawal(String initialAmount, String amount, String expectedBalance) throws WrongAmountException {
        Amount accountAmount = Amount.of(new BigDecimal(initialAmount));
        Amount withdrawAmount = Amount.of(new BigDecimal(amount));
        Amount expectedAmount = Amount.of(new BigDecimal(expectedBalance));

        Account account = new Account(accountAmount);
        account.withdraw(withdrawAmount);

        assertThat(account.getBalance()).isEqualTo(expectedAmount);
    }

    @Test
    @DisplayName("Test 2: after a few withdrawals, the account balance is still correct")
    void returns_true_when_account_balance_is_equal_to_expected_balance_after_a_few_withdrawals() throws WrongAmountException {
        Amount expectedBalance = Amount.of(new BigDecimal("25.50"));

        Account account = new Account(Amount.of(new BigDecimal("100.00")));
        account.withdraw(Amount.of(new BigDecimal("50.50")));
        account.withdraw(Amount.of(new BigDecimal("5.00")));
        account.withdraw(Amount.of(new BigDecimal("17.25")));
        account.withdraw(Amount.of(new BigDecimal("1.75")));

        assertThat(account.getBalance()).isEqualTo(expectedBalance);
    }

    @ParameterizedTest
    @CsvSource({"10,15",
                "99.99,100",
                "125000,126000"})
    @DisplayName("Test 3: an exception is thrown when the amount to withdraw is bigger than balance")
    void throws_wrong_amount_exception_when_trying_to_withdraw_more_than_balance(String balance, String amount) {
        Account account = new Account(Amount.of(new BigDecimal(balance)));
        assertThatThrownBy(() -> account.withdraw(Amount.of(new BigDecimal(amount))))
                .isInstanceOf(WrongAmountException.class)
                .hasMessage("Wrong amount, the amount withdrawn must not be greater than the balance");
    }
}
