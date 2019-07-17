package io.azar.pservice.exception;

public class PasswordRepeatedSubsequenceExistsException extends PasswordComplainceFailureException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8466057694704635983L;

	public PasswordRepeatedSubsequenceExistsException(){
		super();
	}
	
	public PasswordRepeatedSubsequenceExistsException(String msg){
		super(msg);
	}
}
