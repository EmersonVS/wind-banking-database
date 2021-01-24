package com.wind.banking.helpers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.wind.banking.app.controller.account.helpers.form.customer.CustomerForm;
import com.wind.banking.app.controller.account.helpers.form.customer.EnderecoForm;
import com.wind.banking.app.controller.account.helpers.form.customer.TelefoneForm;
import com.wind.banking.app.models.entity.account.Account;
import com.wind.banking.app.models.entity.account.customer.Endereco;
import com.wind.banking.app.models.entity.account.customer.Telefone;
import com.wind.banking.app.models.helpers.AccountHelper;

@SpringBootTest
public class AccountHelperTests {
	
	CustomerForm customerForm1 = new CustomerForm();
	List<TelefoneForm> telefoneForm1 = new ArrayList<TelefoneForm>();
	List<EnderecoForm> enderecoForm1 = new ArrayList<EnderecoForm>();
	
	@BeforeEach
	void SetCustomerInfo() {
		customerForm1.cpf = "cpf";
		customerForm1.rg = "rg";
		customerForm1.nome = "nome";
		EnderecoForm endereco1 = new EnderecoForm();
		TelefoneForm telefone1 = new TelefoneForm();
		enderecoForm1.add(endereco1);
		telefoneForm1.add(telefone1);
	}
	
	@Test
	@DisplayName("Testing CreateAccount method")
	void CreateAccount() {
		Account account1 = AccountHelper.CreateAccount("country1", customerForm1);
		assertNotEquals(account1, null);
	}

	@Test
	@DisplayName("Testing CreateEnderecos method")
	void CreateEnderecos() {
		List<Endereco> enderecos = AccountHelper.CreateEnderecos(enderecoForm1);
		assertNotEquals(enderecos, null);
		assertEquals(enderecos.size(), 1);
	}
	
	@Test
	@DisplayName("Testing CreateTelefones method")
	void CreateTelefones() {
		List<Telefone> telefones = AccountHelper.CreateTelefones(telefoneForm1);
		assertNotEquals(telefones, null);
		assertEquals(telefones.size(), 1);
	}
}
