package com.wind.banking.app.controller.helpers.validators;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wind.banking.app.models.entity.User;
import com.wind.banking.app.models.repository.UserRepository;

@Service
public class UserValidator {

	@Autowired
	private UserRepository userRepository;

	public boolean isUserCreated(User requestedUser) {
		Optional<User> databaseUser = userRepository.findById(requestedUser.getUsername());
		if (databaseUser.isPresent()) {
			return true;
		}
		return false;
	}
	
	public boolean userHasAccount(User requestedUser) {
		Optional<User> databaseUser = userRepository.findById(requestedUser.getUsername());
		if (databaseUser.isPresent()) {
			if (databaseUser.get().getAccount() == null) {
				return false;
			}
		}
		return true;
	}

	public boolean userHasntAccount(User requestedUser) {
		Optional<User> databaseUser = userRepository.findById(requestedUser.getUsername());
		if (databaseUser.isPresent()) {
			if (databaseUser.get().getAccount() == null) {
				return true;
			}
		}
		return false;
	}

	public boolean isBothUsersCreated(User user1, User user2) {
		Optional<User> databaseUser1 = userRepository.findById(user1.getUsername());
		Optional<User> databaseUser2 = userRepository.findById(user2.getUsername());
		if (databaseUser1.isPresent() && databaseUser2.isPresent()) {
			return true;
		}
		return false;
	}
}
