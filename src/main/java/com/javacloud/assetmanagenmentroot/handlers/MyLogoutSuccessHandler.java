package com.javacloud.assetmanagenmentroot.handlers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javacloud.assetmanagenmentroot.response.Response;

@Component
public class MyLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler  {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		Response response2 = new Response();
		response2.setMessage("you are successfully logged out");

		response.setStatus(200);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(response2);
		PrintWriter printWriter = response.getWriter();
		printWriter.write(json);
	}
	
}
