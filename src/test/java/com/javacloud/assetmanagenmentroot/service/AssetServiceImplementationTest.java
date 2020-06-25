package com.javacloud.assetmanagenmentroot.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.javacloud.assetmanagenmentroot.entity.Assets;
import com.javacloud.assetmanagenmentroot.service.AssetServices;


@RunWith(SpringRunner.class)
@SpringBootTest
class AssetServiceImplementationTest {


	@Autowired
	private AssetServices assetServices;
	
	Assets assets = new Assets();
	
	
	@Test
	public void testAddAsset() {
	assets.setAssetId(24);
	assets.setAssetName("Mobile");
	assets.setCategory("Hardware");
	assets.setDetails("Good quality");
	assets.setPrice("8373");
	assets.setQuantity("23");
	Assets check = assetServices.saveAsset(assets);
	Assertions.assertNotNull(check);	
	}
	
	
	@Test
	public void testUpdateAsset() {
	assets.setAssetId(24);
	assets.setAssetName("Mobile");
	assets.setCategory("Hardware");
	assets.setDetails("Good quality");
	assets.setPrice("7800");
	assets.setQuantity("14");
	Assets check = assetServices.saveAsset(assets);
	Assertions.assertNotNull(check);	
	}
	
	@Test
	public void testGetAllAssets() {
		List<Assets> list = assetServices.getAllAssets();
		Assertions.assertNotNull(list);	

	}
	
//	
//	@Test
//	public void testDeleteAsset() {
//	boolean check = assetServices.deleteAsset(24);
//	Assertions.assertTrue(check);
//
//	}


	
	
	

}
