package com.wind.banking.app.controller.account.helpers.DTO.customer;

import com.wind.banking.app.models.entity.account.customer.Telefone;

public class TelefoneDTO {
	
	public String ddd;
	public String numero;
	public String tipo;
	
	public TelefoneDTO(Telefone telefone) {
		this.ddd = telefone.getDdd();
		this.numero = telefone.getNumero();
		this.tipo = telefone.getTipo();
	}
}
