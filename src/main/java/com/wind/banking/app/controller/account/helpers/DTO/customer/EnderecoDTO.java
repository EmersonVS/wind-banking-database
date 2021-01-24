package com.wind.banking.app.controller.account.helpers.DTO.customer;

import com.wind.banking.app.models.entity.account.customer.Endereco;

public class EnderecoDTO {

	public String rua;
	public String numero;
	public String bairro;
	public String cidade;
	public String estado;
	public String cep;

	public EnderecoDTO(Endereco endereco) {
		this.rua = endereco.getRua();
		this.numero = endereco.getNumero();
		this.bairro = endereco.getBairro();
		this.cidade = endereco.getCidade();
		this.estado = endereco.getEstado();
		this.cep = endereco.getCep();
	}

}
