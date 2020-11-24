package kata.sg;

import kata.sg.exception.WrongAmountException;
import kata.sg.model.Account;
import kata.sg.model.Transaction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static kata.sg.model.Operation.DEPOSIT;
import static kata.sg.model.Operation.WITHDRAW;
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

    @Test
    @DisplayName("Test 2: after a few deposits, the transaction history contains all the deposits")
    void returns_true_when_list_of_transaction_contains_all_deposits() throws WrongAmountException {
        BigDecimal firstAmount = new BigDecimal("100");
        BigDecimal secondAmount = new BigDecimal("25.50");
        BigDecimal thirdAmount = new BigDecimal("121.33");

        List<Transaction> expectedTransactionHistory = Arrays.asList(
                new Transaction(LocalDateTime.now(), DEPOSIT, firstAmount),
                new Transaction(LocalDateTime.now(), DEPOSIT, secondAmount),
                new Transaction(LocalDateTime.now(), DEPOSIT, thirdAmount));

        Account account = new Account();
        account.deposit(firstAmount);
        account.deposit(secondAmount);
        account.deposit(thirdAmount);

        assertThat(account.getTransactionHistory()).isEqualTo(expectedTransactionHistory);
    }

    @Test
    @DisplayName("Test 3: after a few transactions, transaction history contains all the transactions")
    void returns_true_when_list_of_transaction_contains_all_transactions() throws WrongAmountException {
        BigDecimal firstAmount = new BigDecimal("1627.45");
        BigDecimal secondAmount = new BigDecimal("25.50");
        BigDecimal thirdAmount = new BigDecimal("121.33");
        BigDecimal fourthAmount = new BigDecimal("46.46");
        BigDecimal fifthAmount = new BigDecimal("827.06");

        List<Transaction> expectedTransactionHistory = Arrays.asList(
                new Transaction(LocalDateTime.now(), DEPOSIT, firstAmount),
                new Transaction(LocalDateTime.now(), DEPOSIT, secondAmount),
                new Transaction(LocalDateTime.now(), WITHDRAW, thirdAmount),
                new Transaction(LocalDateTime.now(), DEPOSIT, fourthAmount),
                new Transaction(LocalDateTime.now(), WITHDRAW, fifthAmount));

        Account account = new Account();
        account.deposit(firstAmount);
        account.deposit(secondAmount);
        account.withdraw(thirdAmount);
        account.deposit(fourthAmount);
        account.withdraw(fifthAmount);

        assertThat(account.getTransactionHistory()).isEqualTo(expectedTransactionHistory);
    }
}
