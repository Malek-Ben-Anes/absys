package com.absys.test.exception;

import lombok.Data;

import java.util.Date;

@Data
public class ExceptionResponse {
	
	private Date timestamp;
	
	private String message;
	
	private String detail;

	protected ExceptionResponse(Date timestamp, String message, String detail) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.detail = detail;
	}

}
