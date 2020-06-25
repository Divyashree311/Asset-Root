package com.javacloud.assetmanagenmentroot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javacloud.assetmanagenmentroot.entity.UserBean;

public interface UserRepository extends JpaRepository<UserBean,Integer> {

	@Query("from UserBean where id=?1 and password=?2")
	UserBean login(int id, String password);
	
	@Query("from UserBean where id=?1")
	UserBean findByUserId(int id);
}
