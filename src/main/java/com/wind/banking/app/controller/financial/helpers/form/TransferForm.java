package com.wind.banking.app.controller.financial.helpers.form;

import java.util.List;

import javax.validation.constraints.Min;

import com.wind.banking.app.models.entity.account.Account;
import com.wind.banking.app.models.helpers.MoneyOperationHelper;
import com.wind.banking.app.models.helpers.TransferError;

public class TransferForm {

	public String destinyId;

	@Min(value=1, message="value must be positive and greater than 1")
	public Double value;
	
	public List<Account> TransferMoney(Account originAccount, Account destinyAccount) throws TransferError {
		MoneyOperationHelper moneyHelper = new MoneyOperationHelper();
		try {
			return moneyHelper.TransferMoneyBetweenAccounts(originAccount, destinyAccount, this.value);
		} catch (TransferError error) {
			error.printStackTrace();
			throw error;
		}
	}

}
