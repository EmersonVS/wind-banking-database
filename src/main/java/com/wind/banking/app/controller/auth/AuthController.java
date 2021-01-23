package com.wind.banking.app.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wind.banking.app.controller.auth.helpers.dto.TokenDTO;
import com.wind.banking.app.controller.auth.helpers.form.UserForm;
import com.wind.banking.app.controller.helpers.validators.UserValidator;
import com.wind.banking.app.models.entity.User;
import com.wind.banking.app.models.repository.UserRepository;
import com.wind.banking.config.security.token.TokenService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserValidator userValidator;

	@PostMapping("/register")
	public ResponseEntity<?> CreateUser(@RequestBody UserForm userForm) {
		User newUser = userForm.mapAsUser();
		if(userValidator.isUserCreated(newUser)) {
			return ResponseEntity.status(400).build();
		}
		userRepository.save(newUser);
		return ResponseEntity.status(201).build();
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> GenerateToken(@RequestBody UserForm userForm) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = userForm.mapAsUsernamePasswordAuthenticationToken();
		try {
			Authentication authenticatedUser = authManager.authenticate(usernamePasswordAuthenticationToken);
			String newToken = tokenService.genToken(authenticatedUser);
			return ResponseEntity.status(200).body(new TokenDTO(newToken));			
		} catch (AuthenticationException error) {
			System.out.println(error);
			return ResponseEntity.badRequest().build();
		}
	}

}
