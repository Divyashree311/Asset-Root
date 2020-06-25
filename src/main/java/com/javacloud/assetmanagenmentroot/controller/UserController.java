package com.javacloud.assetmanagenmentroot.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.javacloud.assetmanagenmentroot.entity.UserBean;
import com.javacloud.assetmanagenmentroot.response.Response;

@SuppressWarnings("unchecked")
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class UserController {

	

	@Autowired
	private RestTemplate restTemplate;

	
	@GetMapping("/user")
	public Response<List<UserBean>> findAllUsers() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8083/api/user", HttpMethod.GET, entity, Response.class).getBody();

	}

	@GetMapping("/user/getusers/{id}")
	public Response<UserBean> getByIdUser(@PathVariable int id) {
		 HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return restTemplate.exchange("http://localhost:8083/api/user/getusers/" + id, HttpMethod.GET, entity, Response.class).getBody();
		
		
	}

	@DeleteMapping("/user/deleteuser/{id}")
	public Response<UserBean> deleteUsers(@PathVariable int id) {
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<UserBean> entity = new HttpEntity<UserBean>(headers);
	      
	      return restTemplate.exchange("http://localhost:8083/api/user/deleteuser/"+id, HttpMethod.DELETE, entity, Response.class).getBody();


	}

	@PutMapping("/user/updateuser")
	public Response<UserBean> updateUsers(@Valid @RequestBody UserBean details) {
		 HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<UserBean> entity = new HttpEntity<UserBean>(details,headers);
	      
	      return restTemplate.exchange("http://localhost:8083/api/user/updateuser", HttpMethod.PUT, entity, Response.class).getBody();
		
		

	}

	
	
	@PostMapping("/user/forgetpwd")
	public Response<UserBean> forgetPwd(@RequestBody UserBean details) {
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      headers.setContentType(MediaType.APPLICATION_JSON);
	      HttpEntity<UserBean> entity = new HttpEntity<UserBean>(details,headers);
	      
	      return restTemplate.exchange("http://localhost:8083/api/user/forgetpwd", HttpMethod.POST, entity, Response.class).getBody();
		      			
	}     
	
	
	@PostMapping("/user/adduser")
	public Response<UserBean> save(@Valid @RequestBody UserBean details) {
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      headers.setContentType(MediaType.APPLICATION_JSON);
	      HttpEntity<UserBean> entity = new HttpEntity<UserBean>(details,headers);
	      
	      return restTemplate.exchange("http://localhost:8083/api/user/adduser", HttpMethod.POST, entity, Response.class).getBody();
	

	}

	@GetMapping("/user/{pageNo}/{usersPerPage}")
	public String getUserss(@PathVariable int pageNo, @PathVariable int usersPerPage) {
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return restTemplate.exchange("http://localhost:8083/api/user/" + pageNo + "/"+ usersPerPage, HttpMethod.GET, entity, String.class).getBody();
	}

	@GetMapping("/user/{pageNo}/{usersPerPage}/{userFieldName}")
	public String getSortedAssets(@PathVariable int pageNo, @PathVariable int usersPerPage,
			@PathVariable String userFieldName) {
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return restTemplate.exchange("http://localhost:8083/api/user/" + pageNo + "/"+ usersPerPage + "/" + userFieldName, HttpMethod.GET, entity, String.class).getBody();
	}	

}
