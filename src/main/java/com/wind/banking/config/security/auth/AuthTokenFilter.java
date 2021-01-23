package com.wind.banking.config.security.auth;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.wind.banking.app.models.entity.User;
import com.wind.banking.app.models.repository.UserRepository;
import com.wind.banking.config.security.token.TokenService;

public class AuthTokenFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	private UserRepository userRepository;
	
	public AuthTokenFilter(TokenService tokenService, UserRepository userRepository) {
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = getToken(request);
		boolean valid = tokenService.isTokenValid(token);
		if(valid) {
			autenticarCliente(token);
		}
		
		filterChain.doFilter(request, response);
	}
	
	private void autenticarCliente(String token) {
		String login = tokenService.getUsername(token);
		User user = userRepository.findById(login).get();
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, null);  
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

	private String getToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) return null;
		return token.substring(7, token.length());
	}

}
