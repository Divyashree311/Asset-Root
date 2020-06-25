package com.javacloud.assetmanagenmentroot.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.javacloud.assetmanagenmentroot.entity.UserBean;

public interface UserService {

    public List<UserBean> findAllUsers();
	
	public UserBean getByIdUser(int id);
	
	public boolean deleteUser(int id);
	
	public UserBean saveUser(UserBean register);
	
	UserBean login(int id,String password);
	
	UserBean findByUserId(int id);
	
	public UserBean update(UserBean userBean);
	
	public Page<UserBean> getUserss(int pageNo, int usersPerPage);
	
	public Page<UserBean> getSortedAssets(int pageNo, int usersPerPage,String userFieldName);
	
	public UserBean forgetPwd(UserBean userBean);
	
}
