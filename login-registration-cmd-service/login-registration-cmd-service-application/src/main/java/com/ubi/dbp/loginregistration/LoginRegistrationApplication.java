package com.ubi.dbp.loginregistration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.ubi.dbp.loginregistration.LoginRegistrationApplication;


@SpringBootApplication
@ComponentScan(basePackages = { "com.ubi.dbp.loginregistration", "dbp.framework.proxy.common.client"})
public class LoginRegistrationApplication {
	public static void main(String[] args) {
        SpringApplication.run(LoginRegistrationApplication.class, args);
	}
	
}
