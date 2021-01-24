package com.wind.banking.app.models.entity.account;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.wind.banking.app.models.entity.User;
import com.wind.banking.app.models.entity.account.customer.Customer;
import com.wind.banking.app.models.entity.account.financial.FinancialError;

@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long accountId;

	private String country;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id", referencedColumnName = "customerId")
	private Customer customer;

	@OneToOne(mappedBy = "account")
	private User user;

	@Column(name = "saldo_cc")
	private Double saldoCC;

	@Column(name = "saldo_pp")
	private Double saldoPP;

	@Deprecated
	public Account() {

	}

	public Account(String country, Customer customer) {
		this.country = country;
		this.customer = customer;
		this.saldoCC = 10.00;
		this.saldoPP = 10.00;
	}

	public Long getAccountId() {
		return accountId;
	}

	public String getCountry() {
		return country;
	}

	public Customer getCustomer() {
		return customer;
	}

	public User getUser() {
		return user;
	}

	public Double getSaldoCC() {
		return saldoCC;
	}

	public Double getSaldoPP() {
		return saldoPP;
	}

	public void WithdrawCC(Double value) throws FinancialError {
		Double newBalance = this.saldoCC - value;
		if (newBalance >= 0) {
			this.saldoCC = newBalance;
		} else {
			throw new FinancialError("Not enough balance");
		}
	}

	public void DepositCC(Double value) throws FinancialError {
		Double newBalance = this.saldoCC + value;
		this.saldoCC = newBalance;

	}

	public void WithdrawPP(Double value) throws FinancialError {
		Double newBalance = this.saldoPP - value;
		if (newBalance >= 0) {
			this.saldoPP = newBalance;
		} else {
			throw new FinancialError("Not enough balance");
		}
	}

	public void DepositPP(Double value) throws FinancialError {
		Double newBalance = this.saldoPP + value;
		this.saldoPP = newBalance;
	}

}
