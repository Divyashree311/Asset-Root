package com.javacloud.assetmanagenmentroot;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.javacloud.assetmanagenmentroot.entity.UserBean;

@SuppressWarnings("serial")
@Component
public class UserDetailsImpl implements UserDetails{
	
	private UserBean userBean;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userBean.getRole());
		return Arrays.asList(authority);
	}

	@Override
	public String getPassword() {
		return userBean.getPassword();
	}

	@Override
	public String getUsername() {
		return userBean.getId() + "";
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
	
	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

}
