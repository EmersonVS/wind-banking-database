package com.wind.banking.app.models.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.wind.banking.app.models.entity.account.Account;

@Entity
public class User implements UserDetails {

	private static final long serialVersionUID = -5890286577047223328L;

	@Id
	@Column(unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	@OneToOne(cascade = CascadeType.ALL)

	@JoinColumn(name = "account_id", referencedColumnName = "accountId")
	private Account account;

	@Deprecated
	public User() {

	}

	public User(String username, String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		this.username = username;
		this.password = passwordEncoder.encode(password);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public Account getAccount() {
		return account;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
