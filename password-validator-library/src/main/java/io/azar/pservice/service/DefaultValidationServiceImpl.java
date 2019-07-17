package io.azar.pservice.service;

import io.azar.pservice.exception.PasswordComplainceFailureException;

/**
 * 
 * @author SK
 *
 */
public class DefaultValidationServiceImpl extends AbstractValidationService {

	@Override
	public void addAdditionalValidationChecks(String password) throws PasswordComplainceFailureException {
		// override and add additional checks
		return;
	}

}
