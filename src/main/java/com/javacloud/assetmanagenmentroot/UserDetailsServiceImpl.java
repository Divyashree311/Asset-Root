package com.javacloud.assetmanagenmentroot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.javacloud.assetmanagenmentroot.service.UserService;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserDetailsImpl detailsImpl = new UserDetailsImpl();
		detailsImpl.setUserBean(userService.getByIdUser(Integer.parseInt(username)));
		return detailsImpl;
	}

}
