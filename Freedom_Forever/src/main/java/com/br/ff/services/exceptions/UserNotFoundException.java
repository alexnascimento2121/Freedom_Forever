package com.br.ff.services.exceptions;

public class UserNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5517595616741523994L;

	public UserNotFoundException(String message) {
        super(message);
    }
}
