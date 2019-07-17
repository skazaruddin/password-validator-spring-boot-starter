package io.azar.pservice.test;

import org.junit.Test;

import io.azar.pservice.exception.PasswordAllowedCharsViolationException;
import io.azar.pservice.exception.PasswordLengthViolationException;
import io.azar.pservice.exception.PasswordRepeatedSubsequenceExistsException;
import io.azar.pservice.service.AbstractValidationService;
import io.azar.pservice.service.DefaultValidationServiceImpl;

public class ValidationServiceUnitTest {

	private AbstractValidationService validationService = new DefaultValidationServiceImpl();

	@Test(expected = NullPointerException.class)
	public void nullPasswordTest() {
		validationService.validatePassword(null);
	}

	@Test(expected = PasswordLengthViolationException.class)
	public void smallLengthPasswordTest() {
		validationService.validatePassword("lessthan5characters");
	}

	@Test(expected = PasswordLengthViolationException.class)
	public void greaterLengthPasswordTest() {
		validationService.validatePassword("Thiswillbegreaterthan12characters");
	}

	@Test(expected = PasswordAllowedCharsViolationException.class)
	public void onlyNumbersPasswordTest() {
		validationService.validatePassword("12345");
	}

	@Test(expected = PasswordAllowedCharsViolationException.class)
	public void onlyAlphabetsPasswordTest() {
		validationService.validatePassword("abcde");
	}

	@Test(expected = PasswordAllowedCharsViolationException.class)
	public void specialCharacterPasswordTest() {
		validationService.validatePassword("@@$_pass12");
	}

	@Test(expected = PasswordRepeatedSubsequenceExistsException.class)
	public void repeatedSequencePasswordFormat1Test() {
		validationService.validatePassword("12abcd4512");
	}

	@Test(expected = PasswordRepeatedSubsequenceExistsException.class)
	public void repeatedSequencePasswordFormat2Test() {
		validationService.validatePassword("abcd12ad");
	}

	@Test(expected = PasswordRepeatedSubsequenceExistsException.class)
	public void testRepeatedSequence3() {
		validationService.validatePassword("azooraxxao12");
	}

	@Test(expected = PasswordRepeatedSubsequenceExistsException.class)
	public void testRepeatedSequence4() {
		validationService.validatePassword("password12pd");
	}

	@Test
	public void correctPasswordFormatTest() {
		validationService.validatePassword("12abcd45");
	}

}
