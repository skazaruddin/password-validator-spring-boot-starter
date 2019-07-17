# password-validator-spring-boot-starter

This project contains three modules:

```bash
password-validator-library
password-validator-spring-boot-autoconfigure
spring-boot-starter-password-validator
```

## Installation

Use Maven to build the project

```bash
mvn clean install
```

## Usage 
### 1. Using in a spring project

#### Add Dependency in pom.xml

````xml
<dependency>
		<groupId>io.azar</groupId>
		<artifactId>password-validator-library</artifactId>
		<version>0.0.1-SNAPSHOT</version>
 </dependency>

````

#### Create a ValidationService Bean
```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.azar.pservice.service.DefaultValidationServiceImpl;
import io.azar.pservice.service.ValidationService;

@Configuration
public class PasswordValidatorConfig {

	@Bean
	public ValidationService validatorService() {
		return new DefaultValidationServiceImpl();
	}
	
}
```
#### Inject the service in controller or any other component to use
```java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.azar.pservice.PasswordDTO;
import io.azar.pservice.exception.PasswordComplainceFailureException;
import io.azar.pservice.service.ValidationService;

@Controller
@RequestMapping("/api/")
public class PasswordValidationController {

	@Autowired
	private ValidationService validationService;

	@PostMapping("/v1/validatePassword")
	@ResponseBody
	public ResponseEntity<String> validatePassword(@RequestBody PasswordDTO password) {

		this.validationService.validatePassword(password.getPassword());
		return new ResponseEntity<String>("Password passed compliance test successfully.", HttpStatus.OK);
	}

	@ExceptionHandler({ PasswordComplainceFailureException.class })
	protected ResponseEntity<String> handlePasswordLengthViolationException(PasswordComplainceFailureException ex) {
		String error = ex.getMessage();
		return new ResponseEntity<String>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

```
### 2. Using in a spring boot project

##### Build the project using "mvn clean install"


````xml
<properties>
		<maven.compiler.target>1.8</maven.compiler.target>
		<maven.compiler.source>1.8</maven.compiler.source>
		<spring-boot.version>1.5.6.RELEASE</spring-boot.version>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<java.version>1.8</java.version>
	</properties>

	<modules>
		<module>password-validator-library</module>
		<module>spring-boot-starter-password-validator</module>
		<module>password-validator-spring-boot-autoconfigure</module>
	</modules>

	<dependencyManagement>
		<dependencies>
		
			<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-dependencies</artifactId>
				<version>${spring-boot.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>

````

#### Add Dependency in pom.xml to your spring project

````xml
<dependency>
		<groupId>io.azar</groupId>
		<artifactId>spring-boot-starter-password-validator</artifactId>
		<version>0.0.1-SNAPSHOT</version>
 </dependency>

````
#### Inject the service in controller or any other component to use
```java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.azar.pservice.PasswordDTO;
import io.azar.pservice.exception.PasswordComplainceFailureException;
import io.azar.pservice.service.ValidationService;

@Controller
@RequestMapping("/api/")
public class PasswordValidationController {

	@Autowired
	private ValidationService validationService;

	@PostMapping("/v1/validatePassword")
	@ResponseBody
	public ResponseEntity<String> validatePassword(@RequestBody PasswordDTO password) {

		this.validationService.validatePassword(password.getPassword());
		return new ResponseEntity<String>("Password passed compliance test successfully.", HttpStatus.OK);
	}

	@ExceptionHandler({ PasswordComplainceFailureException.class })
	protected ResponseEntity<String> handlePasswordLengthViolationException(PasswordComplainceFailureException ex) {
		String error = ex.getMessage();
		return new ResponseEntity<String>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)