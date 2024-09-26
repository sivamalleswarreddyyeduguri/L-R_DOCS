package com.ubi.dbp.loginregistration.bff.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.ubi.dbp.loginregistration.bff.config.LoginRegistrationBFFConfig;
import com.ubi.dbp.loginregistration.bff.constants.LoginRegistrationConstants;
import com.ubi.dbp.loginregistration.bff.dto.FileData;
import com.ubi.dbp.loginregistration.bff.exception.ErrorEnum;
import com.ubi.dbp.loginregistration.bff.exception.LoginRegistrationException;
import com.ubi.dbp.loginregistration.bff.model.TAndCRequest;
import com.ubi.dbp.loginregistration.bff.service.LoginRegistrationBFFService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class LoginRegistrationBFFServiceImpl implements LoginRegistrationBFFService {

	private LoginRegistrationBFFConfig LoginRegistrationAppConfig;
	private static FileData loginRegistrationFileData;

	public LoginRegistrationBFFServiceImpl(LoginRegistrationBFFConfig LoginRegistrationAppConfig) {
		this.LoginRegistrationAppConfig = LoginRegistrationAppConfig;
	}

	@Override
	public Mono<FileData> getTermsAndCondition(TAndCRequest tAndCRequest) {

		log.info("inside getTermsAndCondition()");
		if (Objects.isNull(tAndCRequest)) {
			throw new LoginRegistrationException(ErrorEnum.NO_LOGINREGISTRATION_TYPE);
		} else if (tAndCRequest.getTandCType().equalsIgnoreCase(LoginRegistrationConstants.MODULE_NAME)) {
			log.info("initially LoginRegistrationFileData null...");
			try {
				log.info("Reading data from pdf file...");
				InputStream inputStream = this.getClass().getClassLoader()
						.getResourceAsStream(LoginRegistrationAppConfig.getTermsAndCondition());
				if (inputStream == null) {
					throw new IOException("File not found " + LoginRegistrationAppConfig.getTermsAndCondition());
				}

				byte[] pdfBytes = inputStream.readAllBytes();
				loginRegistrationFileData = new FileData(tAndCRequest.getTandCType().toUpperCase() + "_T&C" + ".pdf",
						Base64.getEncoder().encodeToString(pdfBytes));
				return Mono.just(loginRegistrationFileData);
			} catch (Exception e) {
				log.error("Error processing request {}", e.getMessage());

				throw new LoginRegistrationException(e.getMessage());
			}
		}
		throw new LoginRegistrationException(ErrorEnum.NO_LOGINREGISTRATION_TYPE);

	}

}