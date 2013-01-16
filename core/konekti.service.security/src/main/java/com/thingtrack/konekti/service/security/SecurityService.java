package com.thingtrack.konekti.service.security;

import com.thingtrack.konekti.domain.User;

public interface SecurityService {
	
	public User authenticate(final String username, final String password) throws Exception;

}
