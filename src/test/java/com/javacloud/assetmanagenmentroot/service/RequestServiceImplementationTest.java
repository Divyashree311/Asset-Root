package com.javacloud.assetmanagenmentroot.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.javacloud.assetmanagenmentroot.entity.RequestAsset;
import com.javacloud.assetmanagenmentroot.service.RequestService;


@RunWith(SpringRunner.class)
@SpringBootTest
class RequestServiceImplementationTest {

	
	@Autowired
	private RequestService requestService;
	
	RequestAsset requestAsset = new RequestAsset();
	
	@Test	
	public void testAddRequest() {
		requestAsset.setRequestId(1);
		requestAsset.setAssetId("1");
		requestAsset.setUserId("2");
		requestAsset.setQuantity("4");
		requestAsset.setStatus("pending");
		
	}
	
	@Test
	public void testGettAllAsset() {
		List<RequestAsset>  list = requestService.getAllRequest();
		Assertions.assertNotNull(list);	

	}

	@Test
	public void testUpdateRequest() {
		requestAsset.setRequestId(1);
		requestAsset.setAssetId("1");
		requestAsset.setUserId("2");
		requestAsset.setQuantity("4");
		requestAsset.setStatus("approved");
		
	}
	

}
