package com.wind.banking.app.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wind.banking.app.models.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

}
