package com.wind.banking.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.wind.banking.app.models.entity.account.customer.Telefone;

@SpringBootTest
public class TelefoneTests {

	private Telefone telefone = new Telefone("numero", "ddd", "tipo");

	@Test
	@DisplayName("Testing telefone constructor")
	void TestingCustomerConstructor() {
		Telefone telefone2 = new Telefone("numero2", "ddd", "tipo2");
		assertNotEquals(telefone2, null);
	}

	@Test
	@DisplayName("Testing telefone getter")
	void TestingCustomerGetter() {
		assertEquals(telefone.getDdd(), "ddd");
		assertEquals(telefone.getNumero(), "numero");
		assertEquals(telefone.getTipo(), "tipo");
	}

}
