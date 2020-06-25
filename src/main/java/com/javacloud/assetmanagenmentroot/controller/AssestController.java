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

import com.javacloud.assetmanagenmentroot.entity.Assets;
import com.javacloud.assetmanagenmentroot.response.Response;

@SuppressWarnings("unchecked")
@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api")
public class AssestController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/assets")	
	public Response<List<Assets>> getAllAssets(){
		 HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return restTemplate.exchange("http://localhost:8082/api/assets", HttpMethod.GET, entity, Response.class).getBody();


	}
	
	@GetMapping("/assets/getassets/{asset_id}")
	public Response<Assets> getAssetById(@PathVariable int asset_id) {
		 HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return restTemplate.exchange("http://localhost:8082/api/assets/getassets/" + asset_id, HttpMethod.GET, entity, Response.class).getBody();
		
		
	}
	
	@DeleteMapping("/assets/deleteasset/{asset_id}")
	public Response<Assets> deleteAsset(@PathVariable int asset_id) {
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Assets> entity = new HttpEntity<Assets>(headers);
	      
	      return restTemplate.exchange("http://localhost:8082/api/assets/deleteasset/"+asset_id, HttpMethod.DELETE, entity, Response.class).getBody();

	}
	
	@PutMapping("/assets/updateasset")
	public Response<Assets> updateAsset(@RequestBody Assets assets) {
		 HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Assets> entity = new HttpEntity<Assets>(assets,headers);
	      
	      return restTemplate.exchange("http://localhost:8082/api/assets/updateasset", HttpMethod.PUT, entity, Response.class).getBody();
		
		
	}
	
	@PostMapping("/assets/addasset")
	public Response<Assets> addAsset(@Valid @RequestBody Assets assets) {
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      headers.setContentType(MediaType.APPLICATION_JSON);
	      HttpEntity<Assets> entity = new HttpEntity<Assets>(assets,headers);
	      
	      return restTemplate.exchange("http://localhost:8082/api/assets/addasset", HttpMethod.POST, entity, Response.class).getBody();
	
	}
	
	@GetMapping("/assets/{pageNo}/{assetsPerPage}")
	public String getAssets(@PathVariable int pageNo, @PathVariable int assetsPerPage) {
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return restTemplate.exchange("http://localhost:8082/api/assets/" + pageNo + "/"+ assetsPerPage, HttpMethod.GET, entity, String.class).getBody();
					
	}

	@GetMapping("/assets/{pageNo}/{assetsPerPage}/{assetFieldName}")
	public String getSortedAssets(@PathVariable int pageNo, @PathVariable int assetsPerPage,@PathVariable String assetFieldName){
		HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
	      
	      return restTemplate.exchange("http://localhost:8082/api/assets/" + pageNo + "/"+ assetsPerPage + "/" + assetFieldName, HttpMethod.GET, entity, String.class).getBody();
				}
}
