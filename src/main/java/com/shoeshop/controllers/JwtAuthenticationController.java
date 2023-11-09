package com.shoeshop.controllers;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shoeshop.config.JwtTokenUtil;
import com.shoeshop.models.JwtRequest;
import com.shoeshop.models.JwtResponse;


@RestController
@CrossOrigin
public abstract class JwtAuthenticationController {

	@Autowired
	protected AuthenticationManager authenticationManager;

	@Autowired
	protected JwtTokenUtil jwtTokenUtil;

	@Autowired
	protected UserDetailsService jwtInMemoryUserDetailsService;
	
	protected ResponseEntity<?> handleCreateAuthenticationToken (String username, String password) throws Exception {
		authenticate(username, password);

		final UserDetails userDetails = jwtInMemoryUserDetailsService
				.loadUserByUsername(username);

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	protected void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	protected boolean validateToken (String authorizationHeader, String username) {
		String token = authorizationHeader.replace("Bearer ", "");
		boolean result;
		try {
			result = jwtTokenUtil.validateTokenWithUsername(token, username);
		} catch (Exception e) {
			result = false;
		}
		return result;
	}
	
	protected boolean validateTokenForAdmin (String authorizationHeader) {
		String token = authorizationHeader.replace("Bearer ", "");
		boolean result;
		try {
			result = jwtTokenUtil.validateTokenForAdmin(token);
		} catch (Exception e) {
			result = false;
		}
		return result;
	}
}