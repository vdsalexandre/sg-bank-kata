package kata.sg;

import kata.sg.exception.WrongAmountException;
import kata.sg.model.Account;
import kata.sg.model.Transaction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountHistoryTest {

    // US 3:
    // In order to check my operations
    // As a bank client
    // I want to see the history (operation, date, amount, balance)  of my operations


    @Test
    void name() throws WrongAmountException {
        List<Transaction> expectedTransactionHistory = Collections.singletonList(new Transaction());

        Account account = new Account();
        account.deposit(new BigDecimal("100"));

        assertThat(account.getTransactionHistory()).isEqualTo(expectedTransactionHistory);
    }
}
