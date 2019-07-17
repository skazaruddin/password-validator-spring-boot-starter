package io.azar.pservice.service;

import io.azar.pservice.exception.PasswordComplainceFailureException;

/**
 * 
 * @author SK
 *
 */
public interface ValidationService {

	/**
	 * Receives the password and checks whether or not it follows the compliances
	 * that are set for password:
	 * <p>
	 * If the password compliance fails then {@link PasswordComplainceFailureException}
	 * is thrown.The compliances are following:
	 * <ul>
	 * <li>Must consist of a mixture of lower-case letters and numerical digits
	 * only, with at least one of each.</li>
	 * <li>Must be between 5 and 12 characters in length.</li>
	 * <li>Must not contain any sequence of characters immediately followed by the
	 * same sequence</li>
	 * <ul>
	 * </p>
	 * 
	 * @param password The password that has to be checked for compliance.
	 * @throws PasswordComplainceFailureException If password doesn't follows the
	 *                                        compliances that were laid down.
	 * 
	 */
	public void validatePassword(String password) throws PasswordComplainceFailureException;

}
