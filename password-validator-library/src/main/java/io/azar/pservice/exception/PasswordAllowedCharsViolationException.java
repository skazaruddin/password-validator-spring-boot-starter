package io.azar.pservice.exception;

public class PasswordAllowedCharsViolationException extends PasswordComplainceFailureException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1683367314202434574L;

	public PasswordAllowedCharsViolationException(){
		super();
	}
	
	public PasswordAllowedCharsViolationException(String msg){
		super(msg);
	}
}
