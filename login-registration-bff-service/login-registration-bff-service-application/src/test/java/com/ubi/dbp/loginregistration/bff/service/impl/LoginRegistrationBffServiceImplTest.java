package com.ubi.dbp.loginregistration.bff.service.impl;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Base64;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ubi.dbp.loginregistration.bff.config.LoginRegistrationBFFConfig;
import com.ubi.dbp.loginregistration.bff.dto.FileData;
import com.ubi.dbp.loginregistration.bff.exception.LoginRegistrationException;
import com.ubi.dbp.loginregistration.bff.model.TAndCRequest;
import com.ubi.dbp.loginregistration.bff.service.LoginRegistrationBFFService;

import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
public class LoginRegistrationBffServiceImplTest {
	@Mock
	private LoginRegistrationBFFConfig loginRegistrationBFFConfig;

	@Mock
	private LoginRegistrationBFFService bffService;

	@InjectMocks
	private LoginRegistrationBFFServiceImpl loginRegistrationBFFServiceImpl;

	@Mock
	private FileData loginRegistrationFileData;

	@Test
	public void testGetTermsAndConditionTAndCReqNull() {
		TAndCRequest tAndCRequest = null;
		Throwable exception = assertThrows(LoginRegistrationException.class, () -> {
			loginRegistrationBFFServiceImpl.getTermsAndCondition(tAndCRequest);
		});
		assertNotNull(exception);
	}

	@Test
	public void testGetTermsAndConditionTAndCReqInvalidType() {
		TAndCRequest tAndCRequest = new TAndCRequest("68757545", "Invalid_Name");
		Throwable exception = assertThrows(LoginRegistrationException.class, () -> {
			loginRegistrationBFFServiceImpl.getTermsAndCondition(tAndCRequest);
		});

		assertEquals("Type Should only be LoginRegistration", exception.getMessage());
	}

	@Test
	public void testGetTermsAndConditionTAndCReqValidType() {
		TAndCRequest tAndCRequest = new TAndCRequest("68757545", "LoginRegistration");
		assertEquals(tAndCRequest.getTandCType(), "LoginRegistration");
	}

	@Test
	public void testGetTermsAndConditionTAndCReqValidTypeLowerCase() {
		TAndCRequest tAndCRequest = new TAndCRequest("68757545", "loginregistration");
		assertTrue(tAndCRequest.getTandCType().equalsIgnoreCase("LoginRegistration"));
	}

	@Test
	public void testGetTermsAndConditionWithFileData() {
		TAndCRequest tAndCRequest = new TAndCRequest("68757545", "LoginRegistration");
		loginRegistrationFileData = new FileData("L&R_T&C.pdf", "sample data");

		try {
			setStaticField(LoginRegistrationBFFServiceImpl.class, "loginRegistrationFileData", loginRegistrationFileData);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		LoginRegistrationException exception = assertThrows(LoginRegistrationException.class, ()->{
			loginRegistrationBFFServiceImpl.getTermsAndCondition(tAndCRequest).block();
		});

		
		assertNotNull(exception);

	}

	private void setStaticField(Class<?> clazz, String fieldName, Object value) throws Exception {
		Field field = clazz.getDeclaredField(fieldName);
		field.setAccessible(true);
		field.set(null, value);
	}

	@Test
	public void testGetTermsAndConditionFileDataNotNull() throws Exception {
		when(loginRegistrationBFFConfig.getTermsAndCondition()).thenReturn("TnCDocument/L&R_TandC.pdf");
		;
		InputStream inputStream = bffService.getClass().getClassLoader()
				.getResourceAsStream(loginRegistrationBFFConfig.getTermsAndCondition());
		assertNotNull(inputStream);
	}

	@Test
	public void testGetTermsAndConditionFileDataNull() throws Exception {
		setStaticField(LoginRegistrationBFFServiceImpl.class, "loginRegistrationFileData", null);
		when(loginRegistrationBFFConfig.getTermsAndCondition()).thenReturn("InvalidPath");

		InputStream response = bffService.getClass().getClassLoader()
				.getResourceAsStream(loginRegistrationBFFConfig.getTermsAndCondition());

		assertNull(response);

	}

	@Test
	public void testGetTermsAndConditionFileDataException() throws Exception {
		when(loginRegistrationBFFConfig.getTermsAndCondition()).thenReturn("InvalidPath");

		assertThrows(NullPointerException.class, () -> bffService.getClass().getClassLoader()
				.getResourceAsStream(loginRegistrationBFFConfig.getTermsAndCondition()).readAllBytes());
	}

	@Test
	public void testGetTermsAndConditionFileDataEncode() throws Exception {

		TAndCRequest tAndCRequest = new TAndCRequest("68757545", "LoginRegistration");
		loginRegistrationFileData = new FileData("L&R_T&C.pdf", "sample data");

		setStaticField(LoginRegistrationBFFServiceImpl.class, "loginRegistrationFileData", null);
		when(loginRegistrationBFFConfig.getTermsAndCondition()).thenReturn("TnCDocument/L&R_TandC.pdf");
		;
		InputStream inputStream = bffService.getClass().getClassLoader()
				.getResourceAsStream(loginRegistrationBFFConfig.getTermsAndCondition());
		byte[] pdfBytes = inputStream.readAllBytes();
		FileData expected = new FileData("L&R" + "_T&C" + ".pdf", Base64.getEncoder().encodeToString(pdfBytes));

		assertEquals(loginRegistrationFileData.getFileName(), expected.getFileName());
	}

	@Test
	public void testGetTermsAndCondition() throws Exception {

		TAndCRequest tAndCRequest = new TAndCRequest("68757545", "loginregistration");
		loginRegistrationFileData = new FileData("LOGINREGISTRATION_T&C.pdf", "sample data");

		when(loginRegistrationBFFConfig.getTermsAndCondition()).thenReturn("TnCDocument/L&R_TandC.pdf");
		
		Mono<FileData> response = loginRegistrationBFFServiceImpl.getTermsAndCondition(tAndCRequest);


		assertEquals(loginRegistrationFileData.getFileName(), response.block().getFileName());
	}

}
