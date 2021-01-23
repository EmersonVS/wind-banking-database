package com.wind.banking.app.models.entity.account.customer;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.wind.banking.app.models.entity.account.Account;

@Entity
public class Customer {

	@OneToOne(mappedBy = "customer")
	private Account account;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long customerId;

	@Column(unique = true)
	private String cpf;

	@Column(unique = true)
	private String rg;

	private String nome;

	@ElementCollection
	@CollectionTable(name="telefone",joinColumns=@JoinColumn(name="customer_id"))
	private List<Telefone> telefones;

	@ElementCollection
	@CollectionTable(name="endereco",joinColumns=@JoinColumn(name="customer_id"))
	private List<Endereco> enderecos;

	@Deprecated
	public Customer() {

	}

	public Customer(String cpf, String rg, String nome) {
		this.cpf = cpf;
		this.rg = rg;
		this.nome = nome;
	}

	public Account getAccount() {
		return account;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public String getCpf() {
		return cpf;
	}

	public String getRg() {
		return rg;
	}

	public String getNome() {
		return nome;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

}
