package com.wind.banking.app.controller.financial.helpers.DTO;

import com.wind.banking.app.models.entity.account.Account;

public class FinancialDTO {

	public Double saldoCC;
	public Double saldoPP;
	public String country;
	
	public FinancialDTO(Account account) {
		this.saldoCC = account.getSaldoPP();
		this.saldoPP = account.getSaldoCC();
		this.country = account.getCountry();
	}
	
}
