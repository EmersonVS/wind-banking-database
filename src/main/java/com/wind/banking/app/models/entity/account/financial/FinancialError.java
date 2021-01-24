package com.wind.banking.app.models.entity.account.financial;

public class FinancialError extends Exception {

	private static final long serialVersionUID = 1L;
	
	public FinancialError(String errorMessage) {
		super(errorMessage);
	}
	

}
