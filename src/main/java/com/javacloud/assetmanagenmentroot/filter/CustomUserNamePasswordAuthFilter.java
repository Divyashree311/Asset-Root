package com.javacloud.assetmanagenmentroot.filter;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javacloud.assetmanagenmentroot.entity.UserBean;

public class CustomUserNamePasswordAuthFilter extends UsernamePasswordAuthenticationFilter{
	
	private UserBean userBean;
	
	@Override
	protected String obtainUsername(HttpServletRequest request) {
		if(request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
			userBean = null;
			
			try {
				UserBean bean = getUserInfo(request);
				return bean.getId() + "";
			} catch (IOException e) {
				e.printStackTrace();
				return "";
			}
		}
		return super.obtainUsername(request);
	}
	
	@Override
	protected String obtainPassword(HttpServletRequest request) {
		if(request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
			
			try {
				UserBean bean = getUserInfo(request);
				return bean.getPassword();
			} catch (IOException e) {
				e.printStackTrace();
				return "";
			}

		}
		return super.obtainPassword(request);
	}
	
	private UserBean getUserInfo(HttpServletRequest request) throws IOException {
		
		if(userBean == null) {
			ObjectMapper mapper = new ObjectMapper();
			String json = "";
			BufferedReader reader = request.getReader();
			while(reader.ready()) {
				json = json + reader.readLine();
			}
			userBean = mapper.readValue(json, UserBean.class);
		}
		
			
		
		return userBean;
	}

}
