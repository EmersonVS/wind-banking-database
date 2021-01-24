package com.wind.banking.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.wind.banking.app.models.entity.account.customer.Customer;
import com.wind.banking.app.models.entity.account.customer.Endereco;
import com.wind.banking.app.models.entity.account.customer.Telefone;

@SpringBootTest
public class CustomerTests {
	
	private Customer customer1 = new Customer("cpf", "rg", "nome");
	
	@Test
	@DisplayName("Testing customer constructor")
	void TestingCustomerConstructor() {
		Customer customer2 = new Customer("cpf2", "rg2", "nome2");
		assertNotEquals(customer2, null);
	}
	
	@Test
	@DisplayName("Testing customer getter")
	void TestingCustomerGetter() {
		assertEquals(customer1.getCpf(), "cpf");
		assertEquals(customer1.getRg(), "rg");
		assertEquals(customer1.getNome(), "nome");
	}
	
	@Test
	@DisplayName("Testing account setter")
	void TestingCustomerSetter() {
		List<Endereco> enderecos = new ArrayList<Endereco>();
		List<Telefone> telefones = new ArrayList<Telefone>();
		customer1.setEnderecos(enderecos);
		customer1.setTelefones(telefones);
		assertNotEquals(customer1.getEnderecos(), null);
		assertNotEquals(customer1.getTelefones(), null);
	}
	
}
