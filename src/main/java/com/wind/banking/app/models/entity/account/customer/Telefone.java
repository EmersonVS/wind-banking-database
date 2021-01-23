package com.wind.banking.app.models.entity.account.customer;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Telefone {

	@Column(unique = true)
	private String numero;
	private String ddd;
	private String tipo;
	
	@Deprecated
	public Telefone() {
		
	}
	
	public Telefone(String numero, String ddd, String tipo) {
		this.numero = numero;
		this.ddd = ddd;
		this.tipo = tipo;
	}

	public String getNumero() {
		return numero;
	}

	public String getDdd() {
		return ddd;
	}

	public String getTipo() {
		return tipo;
	}

}
