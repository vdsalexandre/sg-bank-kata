package kata.sg;

import kata.sg.exception.WrongAmountException;
import kata.sg.model.Account;
import kata.sg.model.Amount;
import kata.sg.model.Transaction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountHistoryTest {

    // US 3:
    // In order to check my operations
    // As a bank client
    // I want to see the history (operation, date, amount, balance)  of my operations


    @Test
    @DisplayName("Test 1: after a deposit of an amount of 100, the transaction history contains one deposit of 100")
    void returns_true_when_list_of_transaction_contains_the_good_one_transaction() throws WrongAmountException {
        Amount amount = new Amount("100");

        List<Transaction> expectedTransactionHistory = Collections.singletonList(
                new Transaction(LocalDateTime.now(), amount));

        Account account = new Account();
        account.deposit(amount);

        assertThat(account.getTransactionHistory()).isEqualTo(expectedTransactionHistory);
    }

    @Test
    @DisplayName("Test 2: after a few deposits, the transaction history contains all the deposits")
    void returns_true_when_list_of_transaction_contains_all_deposits() throws WrongAmountException {
        Amount firstAmount = new Amount("100");
        Amount secondAmount = new Amount("25.50");
        Amount thirdAmount = new Amount("121.33");

        List<Transaction> expectedTransactionHistory = Arrays.asList(
                new Transaction(LocalDateTime.now(), firstAmount),
                new Transaction(LocalDateTime.now(), secondAmount),
                new Transaction(LocalDateTime.now(), thirdAmount));

        Account account = new Account();
        account.deposit(firstAmount);
        account.deposit(secondAmount);
        account.deposit(thirdAmount);

        assertThat(account.getTransactionHistory()).isEqualTo(expectedTransactionHistory);
    }

    @Test
    @DisplayName("Test 3: after a few transactions, transaction history contains all the transactions")
    void returns_true_when_list_of_transaction_contains_all_transactions() throws WrongAmountException {
        Amount firstAmount = new Amount("1627.45");
        Amount secondAmount = new Amount("25.50");
        Amount thirdAmount = new Amount("121.33");
        Amount fourthAmount = new Amount("46.46");
        Amount fifthAmount = new Amount("827.06");

        List<Transaction> expectedTransactionHistory = Arrays.asList(
                new Transaction(LocalDateTime.now(), firstAmount),
                new Transaction(LocalDateTime.now(), secondAmount),
                new Transaction(LocalDateTime.now(), thirdAmount.negate()),
                new Transaction(LocalDateTime.now(), fourthAmount),
                new Transaction(LocalDateTime.now(), fifthAmount.negate()));

        Account account = new Account();
        account.deposit(firstAmount);
        account.deposit(secondAmount);
        account.withdraw(thirdAmount);
        account.deposit(fourthAmount);
        account.withdraw(fifthAmount);

        assertThat(account.getTransactionHistory()).isEqualTo(expectedTransactionHistory);
    }
}
