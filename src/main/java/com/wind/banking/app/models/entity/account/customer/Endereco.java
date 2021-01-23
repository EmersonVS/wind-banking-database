package com.wind.banking.app.models.entity.account.customer;

import javax.persistence.Embeddable;

@Embeddable
public class Endereco {

	private String rua;
	private String numero;
	private String bairro;
	private String cidade;
	private String estado;
	private String cep;
	
	@Deprecated
	public Endereco() {
		
	}
	
	public Endereco(String rua, String numero, String bairro, String cidade, String estado, String cep) {
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public String getNumero() {
		return numero;
	}

	public String getBairro() {
		return bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public String getEstado() {
		return estado;
	}

	public String getCep() {
		return cep;
	}

}
