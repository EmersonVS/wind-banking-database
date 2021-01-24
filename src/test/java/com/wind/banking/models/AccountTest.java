package com.wind.banking.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.wind.banking.app.models.entity.account.Account;
import com.wind.banking.app.models.entity.account.customer.Customer;

@SpringBootTest
public class AccountTest {

	
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
}
