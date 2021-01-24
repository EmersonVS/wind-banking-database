package com.wind.banking.app.controller.financial;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wind.banking.app.controller.financial.helpers.DTO.FinancialDTO;
import com.wind.banking.app.controller.financial.helpers.form.RedeemForm;
import com.wind.banking.app.controller.financial.helpers.form.SaveForm;
import com.wind.banking.app.controller.financial.helpers.form.TransferForm;
import com.wind.banking.app.controller.helpers.validators.UserValidator;
import com.wind.banking.app.models.entity.User;
import com.wind.banking.app.models.entity.account.Account;
import com.wind.banking.app.models.helpers.TransferError;
import com.wind.banking.app.models.repository.AccountRepository;
import com.wind.banking.app.models.repository.UserRepository;
import com.wind.banking.config.security.token.TokenService;

@RestController
@RequestMapping("/financial")
public class FinancialController {

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AccountRepository accountRepository;
	
	@GetMapping("/info")
	public ResponseEntity<FinancialDTO> GetBalance(@RequestHeader("Authorization") String token ) {
		String username = tokenService.getUsername(token);
		User requestedUser = userRepository.getOne(username);
		if (userValidator.userHasAccount(requestedUser)) {
			return ResponseEntity.ok(new FinancialDTO(requestedUser.getAccount()));
		}
		return ResponseEntity.status(404).build();
	}

	@PostMapping("/transfer")
	@Transactional
	public ResponseEntity<?> TransferMoney(@RequestBody @Valid TransferForm transferForm, @RequestHeader("Authorization") String token) {
		String username = tokenService.getUsername(token);
		User requestedUser = userRepository.getOne(username);
		User destinyUser = userRepository.getOne(transferForm.destinyId);
		if (userValidator.isBothUsersCreated(requestedUser, destinyUser)) {
			try {
				List<Account> accountList = transferForm.TransferMoney(requestedUser.getAccount(),destinyUser.getAccount());
				accountRepository.saveAll(accountList);
			} catch (TransferError e) {
				return ResponseEntity.status(400).build();
			}
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(400).build();
	}

	@PostMapping("/save")
	@Transactional
	public ResponseEntity<?> SaveMoney(@RequestBody @Valid SaveForm saveForm, @RequestHeader("Authorization") String token) {
		String username = tokenService.getUsername(token);
		User requestedUser = userRepository.getOne(username);
		if (userValidator.isUserCreated(requestedUser)) {
			try {
				Account account = saveForm.SaveMoney(requestedUser.getAccount());
				accountRepository.save(account);
				return ResponseEntity.ok().build();
			} catch (TransferError error) {
				return ResponseEntity.status(400).build();
			}
		}
		return ResponseEntity.status(400).build();
	}

	@PostMapping("/redeem")
	@Transactional
	public ResponseEntity<?> Redeem(@RequestBody @Valid RedeemForm redeemForm, @RequestHeader("Authorization") String token) {
		String username = tokenService.getUsername(token);
		User requestedUser = userRepository.getOne(username);
		if (userValidator.isUserCreated(requestedUser)) {
			try {
				Account account = redeemForm.RedeemMoney(requestedUser.getAccount());
				accountRepository.save(account);
				return ResponseEntity.ok().build();
			} catch (TransferError error) {
				return ResponseEntity.status(400).build();
			}
		}
		return ResponseEntity.status(400).build();
	}
}
