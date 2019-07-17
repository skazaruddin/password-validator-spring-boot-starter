package io.azar.pservice.exception;

public class PasswordLengthViolationException extends PasswordComplainceFailureException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4661234845847808747L;

	public PasswordLengthViolationException(){
		super();
	}
	
	public PasswordLengthViolationException(String msg){
		super(msg);
	}
	
	
}
