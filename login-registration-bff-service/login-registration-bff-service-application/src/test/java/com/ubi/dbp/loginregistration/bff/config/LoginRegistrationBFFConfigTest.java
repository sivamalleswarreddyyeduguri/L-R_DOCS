package com.ubi.dbp.loginregistration.bff.config;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
		"loginRegistration.path.tnc=test-LoginRegistration-path-tnc"
        })

public class LoginRegistrationBFFConfigTest{
	
	private LoginRegistrationBFFConfig loginRegistrationBFFConfig;
	
	@Test
	public void testTermsAndCondition() {
		assertEquals("test-LoginRegistration-path-tnc",loginRegistrationBFFConfig.getTermsAndCondition());
	}

	@Test 
	public void testNotNullValues() {		
		assertNotNull(loginRegistrationBFFConfig.getTermsAndCondition());
	}
	
}
		