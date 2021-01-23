package com.wind.banking.app.models.entity.account;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.wind.banking.app.models.entity.User;
import com.wind.banking.app.models.entity.account.customer.Customer;

@Entity
public class Account {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long accountId;

	private String country;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_id", referencedColumnName = "customerId")
	private Customer customer;

	@OneToOne(mappedBy = "account")
	private User user;
	
	private BigDecimal saldo;
	
	@Deprecated
	public Account() {
		
	}
	
	public Account(String country, Customer customer) {
		this.country = country;
		this.customer = customer;
		this.saldo = new BigDecimal(10.00);
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

	public BigDecimal getSaldo() {
		return saldo;
	}
	
}
