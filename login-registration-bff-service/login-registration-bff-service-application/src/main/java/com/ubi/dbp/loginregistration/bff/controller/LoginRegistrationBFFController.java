package com.ubi.dbp.loginregistration.bff.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ubi.dbp.loginregistration.bff.dto.FileData;
import com.ubi.dbp.loginregistration.bff.model.TAndCRequest;
import com.ubi.dbp.loginregistration.bff.service.LoginRegistrationBFFService;

import dbp.framework.schema.validator.dbpbffabs.DbpAbstractBff;
import dbp.framework.support.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/app/login-registration/api/v1")
@OpenAPIDefinition(info = @Info(title = "APIs", version = "1.0", description = "Documentation APIs v2.0"))
@Tag(name = "LoginRegistration BFF APIs", description = "LoginRegistration BFF APIs to perform various operation on LoginRegistration")
public class LoginRegistrationBFFController extends DbpAbstractBff {

    private LoginRegistrationBFFService bffQryRequestService;

    public LoginRegistrationBFFController(LoginRegistrationBFFService bffQryRequestService){
        this.bffQryRequestService = bffQryRequestService;
    }


    @Operation(summary = "Download TnC Certificate")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Download TnC Certificate", content = {@Content(schema = @Schema(implementation = FileData.class))}),
            @ApiResponse(responseCode = "400", description = "BAD Request", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Data not Found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Server not responded", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})})
    @PostMapping(value = {"/terms-conditions"}, produces = {"application/json"})
    public Mono<FileData> getTermsAndCondition(@RequestBody TAndCRequest tAndCRequest) {
        return this.bffQryRequestService.getTermsAndCondition(tAndCRequest);
    }
    
    
}