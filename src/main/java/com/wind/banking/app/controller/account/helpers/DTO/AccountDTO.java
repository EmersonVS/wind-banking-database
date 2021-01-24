package com.wind.banking.app.controller.account.helpers.DTO;

import com.wind.banking.app.controller.account.helpers.DTO.customer.CustomerDTO;
import com.wind.banking.app.models.entity.account.Account;

public class AccountDTO {

	public String country;
	public CustomerDTO customer;
	
	public AccountDTO(Account account) {
		this.country = account.getCountry();
		this.customer = new CustomerDTO(account.getCustomer());
	}
	
}
