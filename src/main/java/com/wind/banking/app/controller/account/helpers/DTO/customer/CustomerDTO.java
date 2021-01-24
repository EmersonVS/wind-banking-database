package com.wind.banking.app.controller.account.helpers.DTO.customer;

import java.util.List;
import java.util.stream.Collectors;

import com.wind.banking.app.models.entity.account.customer.Customer;

public class CustomerDTO {

	public String cpf;
	public String rg;
	public String nome;
	public List<TelefoneDTO> telefones;
	public List<EnderecoDTO> enderecos;
	
	public CustomerDTO(Customer customer) {
		this.cpf = customer.getCpf();
		this.rg = customer.getNome();
		this.nome = customer.getNome();
		this.telefones = customer.getTelefones().stream().map(telefone -> new TelefoneDTO(telefone)).collect(Collectors.toList());
		this.enderecos = customer.getEnderecos().stream().map(endereco -> new EnderecoDTO(endereco)).collect(Collectors.toList());
	}
}
