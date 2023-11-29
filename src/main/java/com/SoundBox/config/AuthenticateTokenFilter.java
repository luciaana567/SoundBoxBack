package com.SoundBox.config;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.SoundBox.business.UserService;
import com.SoundBox.core.model.User;

public class AuthenticateTokenFilter extends OncePerRequestFilter{
	
	
	private TokenSecurity tokenSecurity;
	private UserService userService;
	
	
	 public AuthenticateTokenFilter(TokenSecurity tokenSecurity,UserService userService) {
		this.tokenSecurity = tokenSecurity;
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = recuperarToken(request);
		boolean valid = tokenSecurity.isTokenValid(token);
		if (valid) {
			authenticateUser(token);
		}
		filterChain.doFilter(request, response);
		
	}
	private void authenticateUser(String token) {
		Integer idUsuario = tokenSecurity.getIdUserAcces(token);
		User userAccess = userService.EntitieFindById(idUsuario);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userAccess, null, userAccess.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(7, token.length());
	}

}
