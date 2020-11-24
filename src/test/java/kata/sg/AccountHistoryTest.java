package kata.sg;

import kata.sg.exception.WrongAmountException;
import kata.sg.model.Account;
import kata.sg.model.Transaction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static kata.sg.model.Operation.DEPOSIT;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountHistoryTest {

    // US 3:
    // In order to check my operations
    // As a bank client
    // I want to see the history (operation, date, amount, balance)  of my operations


    @Test
    @DisplayName("Test 1: after a deposit of an amount of 100, the transaction history contains one deposit of 100")
    void returns_true_when_list_of_transaction_contains_the_good_one_transaction() throws WrongAmountException {
        BigDecimal amount = new BigDecimal("100");

        List<Transaction> expectedTransactionHistory = Collections.singletonList(
                new Transaction(LocalDateTime.now(), DEPOSIT, amount));

        Account account = new Account();
        account.deposit(amount);

        assertThat(account.getTransactionHistory()).isEqualTo(expectedTransactionHistory);
    }
}
