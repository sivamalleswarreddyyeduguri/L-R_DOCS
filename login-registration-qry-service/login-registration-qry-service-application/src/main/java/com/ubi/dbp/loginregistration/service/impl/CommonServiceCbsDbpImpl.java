package com.ubi.dbp.loginregistration.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ubi.dbp.loginregistration.constants.LoginRegistrationConstants;
import com.ubi.dbp.loginregistration.exception.ErrorConst;
import com.ubi.dbp.loginregistration.exception.LoginRegistrationException;
import com.ubi.dbp.loginregistration.model.CheckMbRegistrationReq;
import com.ubi.dbp.loginregistration.model.CustomerInfo;
import com.ubi.dbp.loginregistration.model.DbpResp;
import com.ubi.dbp.loginregistration.model.VerifyMobileResp;
import com.ubi.dbp.loginregistration.service.ArmService;
import com.ubi.dbp.loginregistration.service.CommonServiceCbsDbpService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;


@Service
@Slf4j
public class CommonServiceCbsDbpImpl implements CommonServiceCbsDbpService {

    private CustomerServiceImpl customerServiceImpl;
    private ArmService armService;


    public CommonServiceCbsDbpImpl(CustomerServiceImpl customerServiceImpl, ArmService armService) {
        super();
        this.customerServiceImpl = customerServiceImpl;
        this.armService = armService;
    }


    public Mono<VerifyMobileResp> ConcurrentCallCbsDbp(CheckMbRegistrationReq mobile) {

        Mono<VerifyMobileResp> customerInquiryByMobile = customerServiceImpl.customerInquiryByMobile(mobile);

        Mono<DbpResp> userByMbDbp = armService.getUserByMbDbp(mobile);


        // This Zip will combine both responses as single response
        return Mono.zip(customerInquiryByMobile, userByMbDbp)
                .flatMap(tuple -> {

                    VerifyMobileResp cbsResp = tuple.getT1();
                    DbpResp dbpResp = tuple.getT2();


                    log.info("DBP response: {}" + dbpResp);
                    log.info("CBS response: {}" + cbsResp);


                    if (cbsResp.getCustomerByCifReq() != null) {

                        log.info("cbs not null");

                        return customerServiceImpl.getCustomerAccountsByCif(cbsResp.getCustomerByCifReq())
                                .flatMap(customerAccountsByCif -> {

                                    log.info("Customer Accounts By CIF: {}", customerAccountsByCif);

                                    return handleConditions(cbsResp, dbpResp, customerAccountsByCif);
                                })
                                .switchIfEmpty(handleConditions(cbsResp, dbpResp, null));

                    } else {
                        log.info("cbs  null");

                        return handleConditions(cbsResp, dbpResp, null);

                    }
                });
    }


    /**
     * This method validates does user exists in CBS and DBP
     * Checks weather user has CAA,CCA,ODA account type and active status
     * TODO Need handling for ECIF, RCIF, Constitution code cases
     */
    private Mono<VerifyMobileResp> handleConditions(VerifyMobileResp cbsResp, DbpResp dbpResp, List<CustomerInfo> customerAccountsByCif) {

        log.info("inside handleConditions{}");

        // condition 1  user not found in CBS and DBP

        if (cbsResp.getRespcode().equals(LoginRegistrationConstants.STATUS_CODE_NOT_FOUND) &&
                dbpResp.getRespcode().equals(LoginRegistrationConstants.STATUS_CODE_NOT_FOUND)) {
            log.error("Error: user not found in CBS and DBP");
            return Mono.error(new LoginRegistrationException(ErrorConst.ACCT_NOT_FOUND));

        }

        // condition 2  user not found in CBS and found in DBP

        else if (cbsResp.getRespcode().equals(LoginRegistrationConstants.STATUS_CODE_NOT_FOUND) &&
                !dbpResp.getRespcode().equals(LoginRegistrationConstants.STATUS_CODE_NOT_FOUND)) {
            log.error("Error: Login with Corp ID{}: user not found in CBS and found in DBP");
            return Mono.error(new LoginRegistrationException(ErrorConst.LOGIN_WITH_CORP_ID));

        }

        // condition 3   user found in CBS and not found in DBP

        else if (!cbsResp.getRespcode().equals(LoginRegistrationConstants.STATUS_CODE_NOT_FOUND) &&
                dbpResp.getRespcode().equals(LoginRegistrationConstants.STATUS_CODE_NOT_FOUND)) {

            log.info("executes when user presents in CBS and not in DBP");

            if (customerAccountsByCif != null) {
                log.info("customerAccountsByCif{}" + customerAccountsByCif);
                return Mono.just(cbsResp);
            } else {
                log.error("Error: user found in CBS and not found in DBP and no account type");
                return Mono.error(new LoginRegistrationException(ErrorConst.ACCT_NOT_FOUND));
            }

        }

        // condition 4  user found in CBS and user found in DBP

        else {
            if (customerAccountsByCif != null) {
                log.info("customerAccountsByCif{}" + customerAccountsByCif);
                return Mono.just(cbsResp);
            } else {
                log.error("Error: user found in CBS and user found in DBP and no account type");
                return Mono.error(new LoginRegistrationException(ErrorConst.ACCT_NOT_FOUND));
            }


        }
    }


}
