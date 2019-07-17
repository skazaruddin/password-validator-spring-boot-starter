package io.azar.pservice.service;

import java.util.HashMap;
import java.util.Map;

import io.azar.pservice.exception.PasswordAllowedCharsViolationException;
import io.azar.pservice.exception.PasswordComplainceFailureException;
import io.azar.pservice.exception.PasswordLengthViolationException;
import io.azar.pservice.exception.PasswordRepeatedSubsequenceExistsException;

public abstract class AbstractValidationService implements ValidationService {

	private static final String LENGTH_ERROR_MSG = "Password should be minimum 5 and maximum 12 characters !";
	private static final String ALPHA_NUMERIC_ERROR_MSG = "Password must consist of a mixture of lower-case letters and numerical digits only, with at least one of each.";
	private static final String REPEATING_SEQUENCE_ERROR_MSG = "Password must not contain any sequence of characters immediately followed by the "
			+ "same sequence.";

	public abstract void addAdditionalValidationChecks(String password) throws PasswordComplainceFailureException;

	@Override
	public final void validatePassword(String password) throws PasswordComplainceFailureException {
		if (password == null) {
			throw new NullPointerException("Password cannot be null !");
		}

		testLength(password);
		testCharactersAndNumbers(password);
		testRepeatedSubsequence(password);

		addAdditionalValidationChecks(password);
	}

	protected void testLength(String password) throws PasswordLengthViolationException {
		if (password.length() < 5 || password.length() > 12)
			throw new PasswordLengthViolationException(LENGTH_ERROR_MSG);
	}

	// 1. First it should check it has only small alphabets a-z and digits.
	// 2. Test whether the string has both alphabets and digits
	protected void testCharactersAndNumbers(String password) throws PasswordAllowedCharsViolationException {
		boolean digitFound = false, alphabetFound = false;

		for (Character character : password.toCharArray()) {
			// checking ASCII Range to validate every character is either small letter or a
			// digit.
			if ((character >= 97 && character <= 122) || (character >= 48 && character <= 57)) {
				if (!alphabetFound && Character.isLetter(character)) {
					alphabetFound = true;
				} else if (!digitFound && Character.isDigit(character)) {
					digitFound = true;
				}
			} else {
				throw new PasswordAllowedCharsViolationException(ALPHA_NUMERIC_ERROR_MSG);
			}
		}
		if (!(digitFound && alphabetFound)) {
			throw new PasswordAllowedCharsViolationException(ALPHA_NUMERIC_ERROR_MSG);
		}
	}

	protected void testRepeatedSubsequence(String password) throws PasswordComplainceFailureException {

		Map<Character, Integer> charMap = new HashMap<>();
		for (int index = 0; index < password.length(); index++) {

			int count = charMap.getOrDefault(password.charAt(index), 0);
			charMap.put(password.charAt(index), ++count);

			if (charMap.get(password.charAt(index)) > 2) {
				throw new PasswordRepeatedSubsequenceExistsException(REPEATING_SEQUENCE_ERROR_MSG);
			}
		}
		StringBuilder builder = new StringBuilder();
		// Removing non-repeating characters.
		charMap.forEach((character, counter) -> {
			if (counter >= 2) {
				builder.append(character);
			}
		});

		// pass the formed string and check palindrome. If it is palindrome then
		// password doesn't have repeated subsequence.
		// If it is not palindrome, it has repeating sequence.
		boolean palindrome = isPalindrome(builder.toString(), 0, builder.toString().length() - 1);
		if (!palindrome) {
			throw new PasswordRepeatedSubsequenceExistsException(REPEATING_SEQUENCE_ERROR_MSG);
		}
	}

	protected boolean isPalindrome(String password, int start, int end) {
		if (start >= end) {
			return true;
		}
		return password.charAt(start) == password.charAt(end) && isPalindrome(password, start + 1, end - 1);
	}

}
