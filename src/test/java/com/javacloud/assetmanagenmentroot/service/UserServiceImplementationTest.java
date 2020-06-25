package com.javacloud.assetmanagenmentroot.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.javacloud.assetmanagenmentroot.entity.UserBean;
import com.javacloud.assetmanagenmentroot.service.UserService;


@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplementationTest {


	@Autowired
	private UserService userService;

	UserBean userBean = new UserBean();

	@Test
	public void testAddUser() {
		userBean = new UserBean();
		userBean.setId(16);
		userBean.setFirstName("Divya");
		userBean.setLastName("KN");
		userBean.setEmail("divya@gmail.com");
		userBean.setPassword("Abc@1234");
		userBean.setRole("ROLE_USER");
		UserBean check = userService.saveUser(userBean);
		Assertions.assertNotNull(check);
	}

//	@Test
//	public void testLogin() {
//
//		userBean = userService.login(16, "Abc@1234");
//		Assertions.assertNotNull(userBean);
//
//	}

	@Test
	public void testLoginFalse() {

		userBean = userService.login(0, "1112");
		Assertions.assertNull(userBean);

	}
//
//	@Test
//	public void testUpdateUser() {
//		userBean.setId(16);
//		userBean.setFirstName("Divyashree");
//		userBean.setLastName("K N");
//		userBean.setEmail("divyashree@gmail.com");
//		UserBean check = userService.saveUser(userBean);
//		Assertions.assertNotNull(check);
//
//	}

	@Test
	public void testGetAllUsers() {
		List<UserBean> list = userService.findAllUsers();
		assertNotNull(list);
	}

	@Test
	public void testDeleteUser() {
		boolean check = userService.deleteUser(16);
		Assertions.assertTrue(check);

	}


}
