package com.wind.banking.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.wind.banking.app.models.entity.account.customer.Endereco;

@SpringBootTest

public class EnderecoTests {

	private Endereco endereco1 = new Endereco("rua", "numero", "bairro", "cidade", "estado", "cep");
	
	@Test
	@DisplayName("Testing endereco constructor")
	void TestingCustomerConstructor() {
		Endereco endereco2 = new Endereco("rua2", "numero2", "bairro2", "cidade2", "estado2", "cep2");
		assertNotEquals(endereco2, null);
	}
	
	@Test
	@DisplayName("Testing endereco getter")
	void TestingCustomerGetter() {
		assertEquals(endereco1.getRua(), "rua");
		assertEquals(endereco1.getNumero(), "numero");
		assertEquals(endereco1.getBairro(), "bairro");
		assertEquals(endereco1.getCidade(), "cidade");
		assertEquals(endereco1.getEstado(), "estado");
		assertEquals(endereco1.getCep(), "cep");
	}
	
}
