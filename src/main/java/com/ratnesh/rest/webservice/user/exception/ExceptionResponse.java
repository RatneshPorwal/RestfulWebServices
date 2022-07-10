package com.ratnesh.rest.webservice.user.exception;

import java.util.Date;

public class ExceptionResponse {
	
	private Date timesptamp;
	private String message;
	private String details;
	public Date getTimesptamp() {
		return timesptamp;
	}
	public String getMessage() {
		return message;
	}
	public String getDetails() {
		return details;
	}
	public ExceptionResponse(Date timesptamp, String message, String details) {
		super();
		this.timesptamp = timesptamp;
		this.message = message;
		this.details = details;
	}
	

}
