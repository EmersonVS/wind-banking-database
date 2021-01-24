package com.wind.banking.app.controller.financial.helpers.form;

import javax.validation.constraints.Min;

import com.wind.banking.app.models.entity.account.Account;
import com.wind.banking.app.models.helpers.MoneyOperationHelper;
import com.wind.banking.app.models.helpers.TransferError;

public class RedeemForm {
	
	@Min(value=1, message="value must be positive and greater than 1")
	public Double value;

	public Account RedeemMoney(Account account) throws TransferError {
		MoneyOperationHelper moneyHelper = new MoneyOperationHelper();
		try {
			return moneyHelper.TransferMoneyBetweenBalances(account, "Conta poupança", value);
		} catch (TransferError error) {
			error.printStackTrace();
			throw error;
		}
	}
}
	