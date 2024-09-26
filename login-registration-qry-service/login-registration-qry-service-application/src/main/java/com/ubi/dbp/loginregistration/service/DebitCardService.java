package com.ubi.dbp.loginregistration.service;

import com.ubi.dbp.loginregistration.model.*;
import reactor.core.publisher.Mono;

public interface DebitCardService {
	 
	 public Mono<VerifyDebitCardResp>  verifyDebitCard(VerifyDebitCard verifyDebitCard);

}
