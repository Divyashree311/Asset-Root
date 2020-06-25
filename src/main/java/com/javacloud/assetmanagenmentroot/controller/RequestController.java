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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.javacloud.assetmanagenmentroot.entity.RequestAsset;
import com.javacloud.assetmanagenmentroot.response.Response;

@SuppressWarnings("unchecked")
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class RequestController {

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/request")
	public Response<List<RequestAsset>> getAllAssets() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8081/api/request", HttpMethod.GET, entity, Response.class)
				.getBody();

	}

	@GetMapping("/request/getrequest/{request_id}")
	public Response<RequestAsset> getRequestById(@PathVariable int request_id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8081/api/request/getrequest/" + request_id, HttpMethod.GET,
				entity, Response.class).getBody();

	}

	// @DeleteMapping("/request/deleterequest/{request_id}")
	// public Response<RequestAsset> deleteRequest(@PathVariable int request_id) {
	// HttpHeaders headers = new HttpHeaders();
	// headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	// HttpEntity<RequestAsset> entity = new HttpEntity<RequestAsset>(headers);
	//
	// return
	// restTemplate.exchange("http://localhost:8081/api/request/deleterequest/"+request_id,
	// HttpMethod.DELETE, entity, Response.class).getBody();
	//
	// }

	@PutMapping("/request/updaterequest")
	public Response<RequestAsset> UpdateRequest(@RequestBody RequestAsset requestAsset) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<RequestAsset> entity = new HttpEntity<RequestAsset>(requestAsset, headers);

		return restTemplate
				.exchange("http://localhost:8081/api/request/updaterequest", HttpMethod.PUT, entity, Response.class)
				.getBody();

	}

	@PutMapping("/request/updaterequest/{id}")
	public Response<RequestAsset> UpdateRequest(@PathVariable int id, @RequestBody RequestAsset requestAsset) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<RequestAsset> entity = new HttpEntity<RequestAsset>(requestAsset, headers);

		return restTemplate.exchange("http://localhost:8081/api/request/updaterequest/" + id, HttpMethod.PUT, entity,
				Response.class).getBody();

	}

	@PostMapping("/request/addrequest")
	public Response<RequestAsset> addRequest(@Valid @RequestBody RequestAsset requestAsset) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<RequestAsset> entity = new HttpEntity<RequestAsset>(requestAsset, headers);

		return restTemplate
				.exchange("http://localhost:8081/api/request/addrequest", HttpMethod.POST, entity, Response.class)
				.getBody();
	}

	@PostMapping("/request/addrequest/{id}")
	public Response<RequestAsset> addRequest(@PathVariable int id, @RequestBody RequestAsset requestAsset) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<RequestAsset> entity = new HttpEntity<RequestAsset>(requestAsset, headers);

		return restTemplate
				.exchange("http://localhost:8081/api/request/addrequest/" + id, HttpMethod.POST, entity, Response.class)
				.getBody();

	}

	@GetMapping("/request/get/{status}")
	public List<RequestAsset> generateReport(@PathVariable String status) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate
				.exchange("http://localhost:8081/api/request/get/" + status, HttpMethod.GET, entity, List.class)
				.getBody();

	}

	@GetMapping("/request/{pageNo}/{requestPerPage}")
	public String getRequests(@PathVariable int pageNo, @PathVariable int requestPerPage) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8081/api/request/" + pageNo + "/" + requestPerPage,
				HttpMethod.GET, entity, String.class).getBody();

	}

	@GetMapping("/request/{pageNo}/{requestPerPage}/{requestFieldName}")
	public String getSortedRequests(@PathVariable int pageNo, @PathVariable int requestPerPage,
			@PathVariable String requestFieldName) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate
				.exchange("http://localhost:8081/api/request/" + pageNo + "/" + requestPerPage + "/" + requestFieldName,
						HttpMethod.GET, entity, String.class)
				.getBody();
	}



	@GetMapping("/request/getrequestAllocated/{pageNo}/{itemsPerPage}")
	public String allocatedRequests(@PathVariable int pageNo, @PathVariable int itemsPerPage) {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8081/api/request/getrequestAllocated/" + pageNo + "/" + itemsPerPage,
				HttpMethod.GET, entity, String.class).getBody();

	}

	@GetMapping("/request/getrequestAllocated/{pageNo}/{itemsPerPage}/{fieldName}")
	public String allocatedRequests(@PathVariable int pageNo, @PathVariable int itemsPerPage,
			@PathVariable String fieldName) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate
				.exchange("http://localhost:8081/api/request/getrequestAllocated/" + pageNo + "/" + itemsPerPage + "/" + fieldName,
						HttpMethod.GET, entity, String.class).getBody();
}

	@GetMapping("/request/getrequestRejected/{pageNo}/{itemsPerPage}")
	public String rejectedRequests(@PathVariable int pageNo, @PathVariable int itemsPerPage) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8081/api/request/getrequestRejected/" + pageNo + "/" + itemsPerPage,
				HttpMethod.GET, entity, String.class).getBody();

	}

	@GetMapping("/request/getrequestRejected/{pageNo}/{itemsPerPage}/{fieldName}")
	public String rejectedRequests(@PathVariable int pageNo, @PathVariable int itemsPerPage,
			@PathVariable String fieldName) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate
				.exchange("http://localhost:8081/api/request/getrequestRejected/" + pageNo + "/" + itemsPerPage + "/" + fieldName,
						HttpMethod.GET, entity, String.class)
				.getBody();
	}

	

	@GetMapping("/request/getrequestPending/{pageNo}/{itemsPerPage}")
	public String pendingRequests(@PathVariable int pageNo, @PathVariable int itemsPerPage) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate.exchange("http://localhost:8081/api/request/getrequestPending/" + pageNo + "/" + itemsPerPage,
				HttpMethod.GET, entity, String.class).getBody();

	}

	@GetMapping("/request/getrequestPending/{pageNo}/{itemsPerPage}/{fieldName}")
	public String pendingRequests(@PathVariable int pageNo, @PathVariable int itemsPerPage,
			@PathVariable String fieldName) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		return restTemplate
				.exchange("http://localhost:8081/api/request/getrequestPending/" + pageNo + "/" + itemsPerPage + "/" + fieldName,
						HttpMethod.GET, entity, String.class)
				.getBody();
	}

	
	
	
	
	
	
	
	
	
	

}
