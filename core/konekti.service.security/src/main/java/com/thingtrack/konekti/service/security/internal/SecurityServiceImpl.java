package com.thingtrack.konekti.service.security.internal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.thingtrack.konekti.domain.Role;
import com.thingtrack.konekti.domain.User;
import com.thingtrack.konekti.service.security.SecurityService;

public class SecurityServiceImpl implements SecurityService {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	public User authenticate(String username, String password) throws Exception {
		Authentication authRequest = new UsernamePasswordAuthenticationToken(username, password);
		
		try {
			authRequest = authenticationManager.authenticate(authRequest);
			SecurityContextHolder.getContext().setAuthentication(authRequest);
			
		}

		catch (AuthenticationException e) {
			throw new Exception(e.getLocalizedMessage());
		}

		// Build the user value object
		User authenticatedUser = new User();
		org.springframework.security.core.userdetails.User userInfo = (org.springframework.security.core.userdetails.User) authRequest.getPrincipal();

		authenticatedUser.setUsername(userInfo.getUsername());
		authenticatedUser.setPassword(userInfo.getPassword());

		List<Role> roles = new ArrayList<Role>();
		for (GrantedAuthority authority : userInfo.getAuthorities()) {
			Role role = new Role();
			role.setDescription(authority.getAuthority());
			
		}
		
		authenticatedUser.setRoles(roles);
		
		return authenticatedUser;
	}

}
