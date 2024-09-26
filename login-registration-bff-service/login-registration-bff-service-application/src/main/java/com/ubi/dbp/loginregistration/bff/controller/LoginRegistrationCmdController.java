package com.ubi.dbp.loginregistration.bff.controller;

import com.ubi.dbp.loginregistration.bff.dto.GenericResponse;
import com.ubi.dbp.loginregistration.bff.dto.MbRegistrationReq;
import com.ubi.dbp.loginregistration.bff.dto.SetPasswordReq;
import com.ubi.dbp.loginregistration.bff.dto.SetSecurityQuestion;
import com.ubi.dbp.loginregistration.bff.service.LoginRegistrationBFFCmdService;
import com.ubi.dbp.loginregistration.bff.dto.*;
import com.ubi.dbp.loginregistration.bff.service.LoginRegistrationBFFCmdService;
import dbp.framework.schema.validator.dbpbffabs.DbpAbstractBff;
import dbp.framework.support.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/app/login-registration/api/v1")
@AllArgsConstructor
public class LoginRegistrationCmdController {

    private final LoginRegistrationBFFCmdService loginRegistrationCmdService;

    @Operation(summary = "Set Security Questions")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Verify Tpin Otp", content = {@Content(schema = @Schema(implementation = GenericResponse.class))}),
            @ApiResponse(responseCode = "400", description = "BAD Request", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Data not Found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Server not responded", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})})
    @PostMapping(value = "/security-question", produces = "application/json")
    public Mono<GenericResponse> setSecurityQuestion(@RequestBody SetSecurityQuestion setSecurityQuestion) {
        return loginRegistrationCmdService.setSecurityQuestion(setSecurityQuestion);
    }

    @Operation(summary = "Set Password")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Verify Tpin Otp", content = {@Content(schema = @Schema(implementation = GenericResponse.class))}),
            @ApiResponse(responseCode = "400", description = "BAD Request", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Data not Found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Server not responded", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})})
    @PostMapping(value = "/password", produces = "application/json")
    public Mono<GenericResponse> setPassword(@RequestBody SetPasswordReq setPasswordReq) {
        return loginRegistrationCmdService.setPassword(setPasswordReq);
    }

    @Operation(summary = "Register User")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Verify Tpin Otp", content = {@Content(schema = @Schema(implementation = GenericResponse.class))}),
            @ApiResponse(responseCode = "400", description = "BAD Request", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Data not Found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Server not responded", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})})
    @PostMapping(value = "/register/user", produces = "application/json")
    public Mono<GenericResponse> mbRegistration(@RequestBody MbRegistrationReq mbRegistrationReq) {
        return loginRegistrationCmdService.mbRegistration(mbRegistrationReq);
    }

    @Operation(summary = "Set MPIN")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Verify Tpin Otp", content = {@Content(schema = @Schema(implementation = GenericResponse.class))}),
            @ApiResponse(responseCode = "400", description = "BAD Request", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Data not Found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Server not responded", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})})
    @PostMapping(value = "/mpin", produces = "application/json")
    public Mono<GenericResponse> setMpin(@Valid @RequestBody SetMpinReq mpinReq) {
        return loginRegistrationCmdService.setMpin(mpinReq);
    }

    @Operation(summary = "Set TPIN")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Verify Tpin Otp", content = {@Content(schema = @Schema(implementation = GenericResponse.class))}),
            @ApiResponse(responseCode = "400", description = "BAD Request", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Data not Found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Server not responded", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})})
    @PostMapping(value = "/tpin", produces = "application/json")
    public Mono<GenericResponse> setTPin(@Valid @RequestBody SetTPinReq settpinreq) {
        return loginRegistrationCmdService.setTPin(settpinreq);
    }

}



