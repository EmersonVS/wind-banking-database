package com.wind.banking.app.controller.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wind.banking.app.controller.account.helpers.form.AccountForm;
import com.wind.banking.app.controller.helpers.validators.UserValidator;
import com.wind.banking.app.models.entity.User;
import com.wind.banking.app.models.entity.account.Account;
import com.wind.banking.app.models.entity.account.customer.Endereco;
import com.wind.banking.app.models.entity.account.customer.Telefone;
import com.wind.banking.app.models.repository.UserRepository;
import com.wind.banking.config.security.token.TokenService;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired 
	private UserRepository userRepository;

	@PostMapping("/create")
	@Transactional
	public ResponseEntity<?> GenerateToken(@RequestBody AccountForm accountForm, @RequestHeader("Authorization") String token) {
		String username = tokenService.getUsername(token);
		User requestedUser = userRepository.getOne(username);
		if (userValidator.isUserCreated(requestedUser) && userValidator.userHasntAccount(requestedUser)) {
			Account newAccount = accountForm.CreateAccount();
			List<Endereco> enderecos = accountForm.CreateEnderecos();
			List<Telefone> telefones = accountForm.CreateTelefones();
			requestedUser.setAccount(newAccount);;
			requestedUser.getAccount().getCustomer().setEnderecos(enderecos);
			requestedUser.getAccount().getCustomer().setTelefones(telefones);
			userRepository.save(requestedUser);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(400).build();
	}
}
