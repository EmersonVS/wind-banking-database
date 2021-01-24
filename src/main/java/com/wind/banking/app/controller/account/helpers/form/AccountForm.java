package com.wind.banking.app.controller.account.helpers.form;

import java.util.List;

import com.wind.banking.app.controller.account.helpers.form.customer.CustomerForm;
import com.wind.banking.app.models.entity.account.Account;
import com.wind.banking.app.models.entity.account.customer.Endereco;
import com.wind.banking.app.models.entity.account.customer.Telefone;
import com.wind.banking.app.models.helpers.AccountHelper;

public class AccountForm {
	
	public String country;
	public CustomerForm customer;
	
	public Account CreateAccount() {
		Account newAccount = AccountHelper.CreateAccount(this.country, this.customer);
		return newAccount;
	}
	
	public List<Endereco> CreateEnderecos() {
		List<Endereco> enderecos = AccountHelper.CreateEnderecos(customer.enderecos);
		return enderecos;
	}
	
	public List<Telefone> CreateTelefones() {
		List<Telefone> telefones = AccountHelper.CreateTelefones(customer.telefones);
		return telefones;
	}
	
}
