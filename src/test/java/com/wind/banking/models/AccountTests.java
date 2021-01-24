package com.wind.banking.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.wind.banking.app.models.entity.account.Account;
import com.wind.banking.app.models.entity.account.customer.Customer;
import com.wind.banking.app.models.entity.account.financial.FinancialError;

@SpringBootTest
public class AccountTests {

	
	@SuppressWarnings("deprecation")
	private Customer customer1 = new Customer();
	private Account account1 = new Account("pais", customer1);
	
	@Test
	@DisplayName("Testing account constructor")
	void TestingAccountConstructor() {
		@SuppressWarnings("deprecation")
		Account account2 = new Account();
		assertNotEquals(account2, null);
	}
	
	@Test
	@DisplayName("Testing account getter")
	void TestingAccountGetter() {
		assertEquals(account1.getCountry(), "pais");
		assertNotEquals(account1.getCustomer(), null);
	}

	@Test
	@DisplayName("Testing WithdrawCC method")
	void WithdrawCCMethod() {
		try {
			account1.WithdrawCC(10.00);
			assertEquals(account1.getSaldoCC(), 0);
			assertEquals(account1.getSaldoPP(), 10.00);
		} catch (FinancialError e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Testing DepositCC method")
	void DepositCCMethod() {
		try {
			account1.DepositCC(10.00);
			assertEquals(account1.getSaldoCC(), 20.00);
			assertEquals(account1.getSaldoPP(), 10.00);
		} catch (FinancialError e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Testing WithdrawCC method with invalid final balance")
	void WithdrawCCMethodInvalidBalance() {
		assertThrows(FinancialError.class, () -> {
			account1.WithdrawCC(11.00);
		});
	}
	
	@Test
	@DisplayName("Testing WithdrawPP method")
	void WithdrawPPMethod() {
		try {
			account1.WithdrawPP(10.00);
			assertEquals(account1.getSaldoPP(), 0);
			assertEquals(account1.getSaldoCC(), 10.00);
		} catch (FinancialError e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Testing DepositPP method")
	void DepositPPMethod() {
		try {
			account1.DepositPP(10.00);
			assertEquals(account1.getSaldoPP(), 20.00);
			assertEquals(account1.getSaldoCC(), 10.00);
		} catch (FinancialError e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@DisplayName("Testing WithdrawPP method with invalid final balance")
	void WithdrawPPMethodInvalidBalance() {
		assertThrows(FinancialError.class, () -> {
			account1.WithdrawPP(11.00);
		});
	}
}
