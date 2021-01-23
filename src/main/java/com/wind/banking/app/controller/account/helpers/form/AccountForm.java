package com.wind.banking.app.controller.account.helpers.form;

import java.util.List;

import com.wind.banking.app.controller.account.helpers.form.customer.CustomerInfo;
import com.wind.banking.app.models.entity.account.Account;
import com.wind.banking.app.models.entity.account.customer.Endereco;
import com.wind.banking.app.models.entity.account.customer.Telefone;
import com.wind.banking.app.models.helpers.AccountHelper;

public class AccountForm {
	
	public String country;
	public CustomerInfo customer;
	
	public Account CreateAccount() {
		Account newAccount = AccountHelper.createAccount(this.country, this.customer);
		return newAccount;
	}
	
	public List<Endereco> CreateEnderecos() {
		List<Endereco> enderecos = AccountHelper.createEnderecos(customer.enderecos);
		return enderecos;
	}
	
	public List<Telefone> CreateTelefones() {
		List<Telefone> telefones = AccountHelper.createTelefones(customer.telefones);
		return telefones;
	}
	
}
