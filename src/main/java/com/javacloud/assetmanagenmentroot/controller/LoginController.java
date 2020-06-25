package com.javacloud.assetmanagenmentroot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javacloud.assetmanagenmentroot.entity.UserBean;
import com.javacloud.assetmanagenmentroot.exceptions.IdNotFoundExceptions;
import com.javacloud.assetmanagenmentroot.response.LoginResponse;
import com.javacloud.assetmanagenmentroot.service.JwtUtil;
import com.javacloud.assetmanagenmentroot.service.UserService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api")
public class LoginController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userDetailsService;
	

	@Autowired
	private UserDetailsService userDetailService;
	
	@Autowired
	private JwtUtil jwtUtil; 
	

	@PostMapping("/user/login")
	public LoginResponse<UserBean> login(@RequestBody UserBean userBean) throws Exception{
		
		UserBean userBean2 = userDetailsService.findByUserId(userBean.getId());
		if(userBean2 == null) {
			return new LoginResponse<>(true,"Invalid Credentials",null,null);
		}
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userBean.getId() + "",userBean.getPassword()));
		} catch(DisabledException de) {
			//we should use loggers here
//			System.out.println("User is Disabled");
			throw new IdNotFoundExceptions("Invalid credentials");
			
		} catch(BadCredentialsException bce) {
			//we should  use loggers here
			throw new IdNotFoundExceptions("Invalid credentials");
		
		}// End of try catch
		
		final UserDetails userDetails = userDetailService.loadUserByUsername(userBean.getId() + "");
	
		final String jwt = jwtUtil.generateToken(userDetails);
		
		return new LoginResponse<UserBean>(false,"Login Successful",userBean2,jwt);
		
	}// End of login()

}
