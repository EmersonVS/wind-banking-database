package com.wind.banking.app.models.helpers;

import java.util.List;
import java.util.stream.Collectors;

import com.wind.banking.app.controller.account.helpers.form.customer.CustomerForm;
import com.wind.banking.app.controller.account.helpers.form.customer.EnderecoForm;
import com.wind.banking.app.controller.account.helpers.form.customer.TelefoneForm;
import com.wind.banking.app.models.entity.account.Account;
import com.wind.banking.app.models.entity.account.customer.Customer;
import com.wind.banking.app.models.entity.account.customer.Endereco;
import com.wind.banking.app.models.entity.account.customer.Telefone;

public class AccountHelper {
	
	public static Account CreateAccount(String country, CustomerForm customerForm) {
		Customer newCustomer = new Customer(customerForm.cpf, customerForm.rg, customerForm.nome);
		Account newAccount = new Account(country, newCustomer);
		return newAccount;
	}
	
	public static List<Endereco> CreateEnderecos(List<EnderecoForm> enderecosForm) {
		List<Endereco> enderecos = enderecosForm.stream().map(endereco -> new Endereco(endereco.rua, endereco.numero, endereco.bairro, endereco.cidade, endereco.estado, endereco.cep)).collect(Collectors.toList());
		return enderecos;
	}
	
	public static List<Telefone> CreateTelefones(List<TelefoneForm> telefonesForm) {
		List<Telefone> telefones = telefonesForm.stream().map(telefone -> new Telefone(telefone.numero, telefone.ddd, telefone.tipo)).collect(Collectors.toList());
		return telefones;
	}
	
}
