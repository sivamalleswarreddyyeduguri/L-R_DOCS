
package com.ubi.dbp.loginregistration.service.impl;


import org.springframework.stereotype.Service;
import com.ubi.dbp.loginregistration.exception.ErrorConst;
import com.ubi.dbp.loginregistration.exception.LoginRegistrationException;
import com.ubi.dbp.loginregistration.model.ArmResp;
import com.ubi.dbp.loginregistration.model.CheckMbRegistrationReq;
import com.ubi.dbp.loginregistration.model.DbpResp;
import com.ubi.dbp.loginregistration.service.ArmService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@AllArgsConstructor
public class ArmServiceImpl implements ArmService {


    @Override
    public Mono<DbpResp> getUserByMbDbp(CheckMbRegistrationReq mobile) {

        log.info("inside getCustomerByCifDbp()");

        if (mobile.getMobile().equals("9999999999")) {
            DbpResp dbpResp = new DbpResp();
            dbpResp.setCif("8738298");
            dbpResp.setMobileNumber(mobile.getMobile());
            dbpResp.setName("Rohit Sharma");
            dbpResp.setEmail("rohit@gmail.com");

            return Mono.just(dbpResp);

        } else {
            log.error("Error: no user found for this mobile number: " + mobile.getMobile());
            DbpResp dbpResp = new DbpResp();
            dbpResp.setRespcode("404");
            return Mono.just(dbpResp);

        }
    }
}











