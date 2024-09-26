package com.ubi.dbp.loginregistration.bff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "dbp.framework.proxy.common.client", "dbp.framework.schema.validator",
		"com.ubi.dbp.loginregistration.bff.*", "dbp.framework.schema" , "com.ubi.dbp.security.filter"})
public class LoginRegistrationBffServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(LoginRegistrationBffServiceApplication.class, args);
	}
}
