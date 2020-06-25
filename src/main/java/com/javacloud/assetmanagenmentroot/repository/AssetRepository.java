package com.javacloud.assetmanagenmentroot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javacloud.assetmanagenmentroot.entity.Assets;

public interface AssetRepository extends JpaRepository<Assets,Integer>{

}
