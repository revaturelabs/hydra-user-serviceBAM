package com.revature.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * @author Unknown
 * Last updated by: John Talanian, April 9th 2018
 * Updated Java Docs
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class AuthUserException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private final String message;
	private final HttpStatus httpStatus;
	
	/**
	 * @author Unknown
	 * @param message String
	 * @param HttpStatus httpStatus
	 * Last updated by: John Talanian, April 9th 2018
	 * Updated Java Docs
	 */
	public AuthUserException(String message, HttpStatus httpStatus) {
		this.message = message;
		this.httpStatus = httpStatus;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
