package com.wind.banking.helpers;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.wind.banking.app.models.entity.account.Account;
import com.wind.banking.app.models.entity.account.customer.Customer;
import com.wind.banking.app.models.helpers.MoneyOperationHelper;
import com.wind.banking.app.models.helpers.TransferError;

@SpringBootTest
public class MoneyOperationHelperTests {

	@SuppressWarnings("deprecation")
	Account account1 = new Account("countryOrigin", new Customer());
	@SuppressWarnings("deprecation")
	Account account2 = new Account("countryDestiny", new Customer());

	@Test
	@DisplayName("Testing TransferMoneyBetweenAccount method account return position")
	void TransferMoneyBetweenAcountsReturnPosition() {
		try {
			List<Account> accountList1 = MoneyOperationHelper.TransferMoneyBetweenAccounts(account1, account2, 1.00);
			assertEquals(accountList1.get(0).getCountry(), "countryOrigin");
			assertEquals(accountList1.get(1).getCountry(), "countryDestiny");
		} catch (TransferError e) {
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("TransferMoneyBetweenAccount method final balance")
	void TransferMoneyBetweenAcountsFinalBalance() {
		try {
			List<Account> accountList1 = MoneyOperationHelper.TransferMoneyBetweenAccounts(account1, account2, 1.00);
			assertEquals(accountList1.get(0).getSaldoCC(), 9.00);
			assertEquals(accountList1.get(1).getSaldoCC(), 11.00);
		} catch (TransferError e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("TransferMoneyBetweenAccount method invalid value")
	void TransferMoneyBetweenAccountsInvalidValue() {
		assertThrows(TransferError.class, () -> {
			MoneyOperationHelper.TransferMoneyBetweenAccounts(account1, account2, -1.00);
		});
	}
	
	@Test
	@DisplayName("TransferMoneyBetweenAccount method greater value than allowed balance")
	void TransferMoneyBetweenAccountsGreaterValue() {
		assertThrows(TransferError.class, () -> {
			MoneyOperationHelper.TransferMoneyBetweenAccounts(account1, account2, 11.00);
		});
	}

	@Test
	@DisplayName("TransferMoneyBetweenBalance method final balance")
	void TransferMoneyBetweenBalancesFinalBalance() {
		try {
			Account account3 = MoneyOperationHelper.TransferMoneyBetweenBalances(account1, "Conta corrente", 1.00);
			assertEquals(account3.getSaldoCC(), 9.00);
			assertEquals(account3.getSaldoPP(), 11.00);
			Account account4 = MoneyOperationHelper.TransferMoneyBetweenBalances(account2, "Conta poupança", 1.00);
			assertEquals(account4.getSaldoCC(), 11.00);
			assertEquals(account4.getSaldoPP(), 9.00);
		} catch (TransferError e) {
			e.printStackTrace();
		}
	}

	@Test
	@DisplayName("TransferMoneyBetweenBalance method invalid value")
	void TransferMoneyBetweenBalancesInvalidValue() {
		assertThrows(TransferError.class, () -> {
			MoneyOperationHelper.TransferMoneyBetweenBalances(account1, "Conta corrente", -1.00);
		});
		assertThrows(TransferError.class, () -> {
			MoneyOperationHelper.TransferMoneyBetweenBalances(account1, "Conta poupança", -1.00);
		});
	}
	
	@Test
	@DisplayName("TransferMoneyBetweenBalance method greater value than allowed balance")
	void TransferMoneyBetweenBalancesGreaterValue() {
		assertThrows(TransferError.class, () -> {
			MoneyOperationHelper.TransferMoneyBetweenBalances(account1, "Conta corrente", 11.00);
		});
		assertThrows(TransferError.class, () -> {
			MoneyOperationHelper.TransferMoneyBetweenBalances(account1, "Conta poupança", 11.00);
		});
	}
}
