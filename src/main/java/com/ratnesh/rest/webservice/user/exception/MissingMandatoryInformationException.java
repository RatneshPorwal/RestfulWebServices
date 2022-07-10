package com.ratnesh.rest.webservice.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MissingMandatoryInformationException extends RuntimeException {

	public MissingMandatoryInformationException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
