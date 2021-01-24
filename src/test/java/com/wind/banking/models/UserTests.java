package com.wind.banking.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.wind.banking.app.models.entity.User;
import com.wind.banking.app.models.entity.account.Account;

@SpringBootTest
public class UserTests {

	
	private User user1 = new User("user1", "password1");
	@SuppressWarnings("deprecation")
	private Account account1 = new Account();
	
	@Test
	@DisplayName("Testing user constructor")
	void TestingUserConstructor() {
		User user2 = new User("user2", "password2");
		assertEquals(user2.getUsername(), "user2");
		assertNotEquals(user2.getPassword(), "password2");
	}
	
	@Test
	@DisplayName("Testing getters")
	void TestingUserGetters() {
		assertEquals(user1.getUsername(), "user1");
		assertNotEquals(user1.getPassword(), "password1");
	}
	
	@Test
	@DisplayName("Testing account setter")
	void TestingUserAccountSetter() {
		user1.setAccount(account1);
		assertNotEquals(user1.getAccount(), null);
	}
	
}
