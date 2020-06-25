package com.javacloud.assetmanagenmentroot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.javacloud.assetmanagenmentroot.entity.RequestAsset;
import com.javacloud.assetmanagenmentroot.entity.UserBean;
import com.javacloud.assetmanagenmentroot.repository.RequestRepository;
import com.javacloud.assetmanagenmentroot.repository.UserRepository;

@Service
public class RequestServiceImplementation implements RequestService{

	private RequestRepository requestRepository;
	
	private UserRepository userRepository;
	
	@Autowired
	public RequestServiceImplementation(RequestRepository requestRepository,UserRepository userRepository) {
		this.requestRepository = requestRepository;
		this.userRepository = userRepository;
	}
	
	@Override
	public List<RequestAsset> getAllRequest() {
		return requestRepository.findAll();
	}

	@Override
	public RequestAsset getRequestById(int request_id) {
		Optional<RequestAsset> result = requestRepository.findById(request_id);
		RequestAsset requestAsset;
		if(result.isPresent()) {
			requestAsset = result.get();
		} else {
			throw new RuntimeException("Request id not found");
		}
		return requestAsset;
	}

	@Override
	public void deleteRequest(int request_id) {
		requestRepository.deleteById(request_id);
	}

	@Override
	public RequestAsset saveAsset(RequestAsset requestAsset) {
		return requestRepository.save(requestAsset);
	}

	@Override
	public Page<RequestAsset> getRequests(int pageNo, int requestsPerPage) {
		Pageable pageable = PageRequest.of(pageNo, requestsPerPage);
		return requestRepository.findAll(pageable);
	}

	@Override
	public Page<RequestAsset> getSortedRequests(int pageNo, int requestsPerPage, String requestFieldName) {
		Pageable pageable = PageRequest.of(pageNo, requestsPerPage, Sort.by(requestFieldName));
		return requestRepository.findAll(pageable);
	}

	@Override
	public RequestAsset addRequest(int id, RequestAsset request) {
		UserBean userBean = userRepository.findById(id).get();
		if(userBean != null) {
			request.setRefId(userBean.getId());
			request.setUserBean(userBean);
		}
		return requestRepository.save(request);
	}

	@Override
	public List<RequestAsset> generateReport(String status) {
		return requestRepository.generateReport(status);
	}
	
	
	

//	@Override
//	public RequestAsset setAccept(int id) {
//		Optional<RequestAsset> result = requestRepository.findById(id);
//		RequestAsset requestAsset = null;
//		requestAsset = result.get();
//		requestAsset.setStatus("Approved");
//		requestRepository.save(requestAsset);		
//		return requestAsset;
//	}

//	@Override
//	public RequestAsset setReject(int id) {
//		Optional<RequestAsset> result = requestRepository.findById(id);
//		RequestAsset requestAsset = null;
//		requestAsset = result.get();
//		requestAsset.setStatus("Rejected");
//		requestRepository.save(requestAsset);		
//		return requestAsset;
//	}

	@Override
	public Page<RequestAsset> allocatedRequests(int pageNo, int itemsPerPage) {
		Pageable pageable = PageRequest.of(pageNo, itemsPerPage);
		return requestRepository.allocatedRequests(pageable);
	}

	@Override
	public Page<RequestAsset> allocatedRequests(int pageNo, int itemsPerPage, String fieldName) {
		Pageable pageable = PageRequest.of(pageNo, itemsPerPage, Sort.by(fieldName));
		return requestRepository.allocatedRequests(pageable);

	}

	@Override
	public Page<RequestAsset> rejectedRequests(int pageNo, int itemsPerPage) {
		Pageable pageable = PageRequest.of(pageNo, itemsPerPage);
		return requestRepository.rejectedRequests(pageable);
	}

	@Override
	public Page<RequestAsset> rejectedRequests(int pageNo, int itemsPerPage, String fieldName) {
		Pageable pageable = PageRequest.of(pageNo, itemsPerPage, Sort.by(fieldName));
		return requestRepository.rejectedRequests(pageable);

	}

	

	@Override
	public Page<RequestAsset> pendingRequests(int pageNo, int itemsPerPage) {
		Pageable pageable = PageRequest.of(pageNo, itemsPerPage);

		return requestRepository.pendingRequests(pageable);
	}

	@Override
	public Page<RequestAsset> pendingRequests(int pageNo, int itemsPerPage, String fieldName) {
		Pageable pageable = PageRequest.of(pageNo, itemsPerPage, Sort.by(fieldName));

		return requestRepository.pendingRequests(pageable);
	}


	

//	@Override
//	public RequestAsset allocateAsset(int request_id) {
//	Optional<RequestAsset> result = requestRepository.findById(request_id);
//	RequestAsset requestAsset = null;
//	requestAsset = result.get();
//	requestAsset.setStatus("Approved");
//	requestRepository.save(requestAsset);		
//	return requestAsset;
//}
//
//	@Override
//	public RequestAsset unallocateAsset(int request_id) {
//	Optional<RequestAsset> result = requestRepository.findById(request_id);
//	RequestAsset requestAsset = null;
//	requestAsset = result.get();
//	requestAsset.setStatus("Rejected");
//	requestRepository.save(requestAsset);		
//	return requestAsset;
//}

	
}
