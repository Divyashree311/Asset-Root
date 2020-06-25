package com.javacloud.assetmanagenmentroot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.javacloud.assetmanagenmentroot.entity.UserBean;
import com.javacloud.assetmanagenmentroot.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService{
	
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Autowired
	public UserServiceImplementation(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<UserBean> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public UserBean getByIdUser(int id) {
		Optional<UserBean> result = userRepository.findById(id);
		UserBean details;
		if(result.isPresent()) {
			details = result.get();
		} else {
			 throw new RuntimeException("Id not found");
		}
		return details;

	}

	@Override
	public boolean deleteUser(int id) {
		userRepository.deleteById(id);
		return true;
	}

	@Override
	public UserBean saveUser(UserBean user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	
	@Override
	public UserBean login(int id, String password) {
		return userRepository.login(id,password);
		//return null;
	}

	@Override
	public UserBean findByUserId(int id) {
		
		UserBean userBean = userRepository.findByUserId(id);
		return userBean;
	}

	@Override
	public Page<UserBean> getUserss(int pageNo, int usersPerPage) {
		Pageable pageable = PageRequest.of(pageNo, usersPerPage);
		return userRepository.findAll(pageable);
	}

	@Override
	public Page<UserBean> getSortedAssets(int pageNo, int usersPerPage, String userFieldName) {
		Pageable pageable = PageRequest.of(pageNo, usersPerPage, Sort.by(userFieldName));
		return userRepository.findAll(pageable);	
	}

	@Override
	public UserBean update(UserBean userBean) {
		return userRepository.save(userBean);
	}

	@Override
	public UserBean forgetPwd(UserBean userBean) {
		UserBean bean = userRepository.findById(userBean.getId()).get();
		if(bean != null) {
			bean.setPassword(passwordEncoder.encode(userBean.getPassword()));
		}
		return userRepository.save(bean);
	}
	
}

