package br.com.codenation.central.service.exception;

public class LogNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public LogNotFoundException(String message) {
		super(message);
	}

}
