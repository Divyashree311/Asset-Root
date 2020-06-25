package com.javacloud.assetmanagenmentroot.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.javacloud.assetmanagenmentroot.entity.Assets;
import com.javacloud.assetmanagenmentroot.entity.RequestAsset;
import com.javacloud.assetmanagenmentroot.entity.UserBean;
import com.javacloud.assetmanagenmentroot.response.ErrorResponse;
import com.javacloud.assetmanagenmentroot.response.LoginResponse;
import com.javacloud.assetmanagenmentroot.response.Response;

@ControllerAdvice
public class GenericException {
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class) 
	public ResponseEntity<?> handleCustomValidationError(MethodArgumentNotValidException ex ){
			ErrorResponse error = new ErrorResponse(true, ex.getBindingResult().getFieldError().getDefaultMessage(),null);
			
			return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	public ResponseEntity<Response<Assets>> handleException(AssetNotFoundExceptions exception) {

		Response<Assets> response = new Response<>(true, exception.getMessage(), null);

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler
	public ResponseEntity<Response<UserBean>> handleException(UserNotFoundExceptions exception) {

		Response<UserBean> response = new Response<>(true, exception.getMessage(), null);

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler
	public ResponseEntity<Response<RequestAsset>> handleException(RequestNotFoundExceptions exception) {

		Response<RequestAsset> response = new Response<>(true, exception.getMessage(), null);

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

	}

	
	@ExceptionHandler
	public ResponseEntity<LoginResponse<UserBean>> handleException(IdNotFoundExceptions exception) {

		LoginResponse<UserBean> response = new LoginResponse<>(true, exception.getMessage(), null,null);

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

	}

}
