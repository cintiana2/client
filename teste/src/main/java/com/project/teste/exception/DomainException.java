package com.project.teste.exception;

public class DomainException extends Exception {
	
	private static final long serialVersionUID = -411205688216768978L;
	private String message;
	
	public DomainException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
   
}
