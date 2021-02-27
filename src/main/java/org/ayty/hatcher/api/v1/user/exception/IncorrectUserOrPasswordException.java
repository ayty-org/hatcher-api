package org.ayty.hatcher.api.v1.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncorrectUserOrPasswordException extends RuntimeException{	
	private static final long serialVersionUID = 1L;
	
	public IncorrectUserOrPasswordException(String msg) {
		super(msg);
	}
	public IncorrectUserOrPasswordException() {
		super();
	}
}