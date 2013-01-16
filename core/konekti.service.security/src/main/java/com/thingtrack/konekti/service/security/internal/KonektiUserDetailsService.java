package com.thingtrack.konekti.service.security.internal;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.thingtrack.konekti.dao.api.UserDao;
import com.thingtrack.konekti.domain.User;

public class KonektiUserDetailsService implements UserDetailsService {
	@Autowired
	private UserDao userDao;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		User user = null;
		
		try {
			user = userDao.getByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(user == null)
			throw new UsernameNotFoundException("User not found in the database");
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true, true, true, new ArrayList<GrantedAuthority>());		
	}

}
