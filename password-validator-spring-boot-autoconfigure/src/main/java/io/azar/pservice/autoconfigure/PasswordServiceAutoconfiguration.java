package io.azar.pservice.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.azar.pservice.service.DefaultValidationServiceImpl;
import io.azar.pservice.service.ValidationService;

@Configuration
public class PasswordServiceAutoconfiguration {

	@Bean
	@ConditionalOnMissingBean
	public ValidationService validationService() {
		return new DefaultValidationServiceImpl();
	}

}
