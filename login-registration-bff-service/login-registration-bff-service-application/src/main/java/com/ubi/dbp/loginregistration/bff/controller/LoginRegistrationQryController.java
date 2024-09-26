package com.ubi.dbp.loginregistration.bff.controller;

import com.ubi.dbp.loginregistration.bff.dto.*;
import com.ubi.dbp.loginregistration.bff.service.LoginRegistrationBFFQryService;
import dbp.framework.support.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/app/login-registration/api/v1")
@AllArgsConstructor
@OpenAPIDefinition(info = @Info(title = "APIs", version = "1.0", description = "Documentation APIs v2.0"))
@Tag(name = "LoginRegistration BFF APIs", description = "LoginRegistration BFF APIs to perform various operation on LoginRegistration")
@Slf4j
public class LoginRegistrationQryController {


    private final LoginRegistrationBFFQryService loginRegistrationBFFQryService;


    @Operation(summary = "Check Existing User")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Check Existing User", content = {@Content(schema = @Schema(implementation = VerifyMobileResp.class))}),
            @ApiResponse(responseCode = "400", description = "BAD Request", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Data not Found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Server not responded", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})})
    @PostMapping(value = "/verify-mobile", produces = {"application/json"})
    public Mono<VerifyMobileResp> checkExistingUser(@Valid @RequestBody CheckMbRegistrationReq mobile) {
        return loginRegistrationBFFQryService.checkExistingUser(mobile);
    }

    @Operation(summary = "Verify User")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Verify User", content = {@Content(schema = @Schema(implementation = UserRegistrationResp.class))}),
            @ApiResponse(responseCode = "400", description = "BAD Request", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Data not Found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Server not responded", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})})
    @PostMapping(value = {"/verify-user"}, produces = {"application/json"})
    public Mono<UserRegistrationResp> verifyUserRegistrationDetails(@Valid @RequestBody UserRegistrationReq userRegistrationReq) {
        return loginRegistrationBFFQryService.verifyUserRegistrationDetails(userRegistrationReq);
    }

    @Operation(summary = "Get Security Questions")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Get Security Questions", content = {@Content(schema = @Schema(implementation = GetSecurityQuestionsResp.class))}),
            @ApiResponse(responseCode = "400", description = "BAD Request", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Data not Found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Server not responded", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})})
    @PostMapping(value = "/get-security-questions", produces = "application/json")
    public Mono<GetSecurityQuestionsResp> getSecurityQuestions(@RequestBody GetSecurityQuestionReq securityQuestionReq) {
        return loginRegistrationBFFQryService.getSecurityQuestions(securityQuestionReq);
    }

    @Operation(summary = "Corp USer Login")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Corp USer Login", content = {@Content(schema = @Schema(implementation = CorpLoginResp.class))}),
            @ApiResponse(responseCode = "400", description = "BAD Request", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Data not Found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Server not responded", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})})
    @PostMapping(value = "/corpLogin", produces = "application/json")
    public Mono<CorpLoginResp> corpLogin(@RequestBody CoprpLoginReq CoprpLoginReq) {
        return loginRegistrationBFFQryService.corpLogin(CoprpLoginReq);
    }

    @Operation(summary = "generate tpin otp")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Generate Tpin Otp", content = {@Content(schema = @Schema(implementation = GenerateOtpResp.class))}),
            @ApiResponse(responseCode = "400", description = "BAD Request", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Data not Found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Server not responded", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})})
    @PostMapping(value = "/generatetpinotp")
    public Mono<GenerateOtpResp> generateTpinOtp(@Valid @RequestBody GenerateOtpReq generateOtpReq) {
        return loginRegistrationBFFQryService.generateTpinOtp(generateOtpReq);
    }

    @Operation(summary = "verify tpin otp")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Verify Tpin Otp", content = {@Content(schema = @Schema(implementation = VerifyOtpResp.class))}),
            @ApiResponse(responseCode = "400", description = "BAD Request", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Data not Found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Server not responded", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})})
    @PostMapping(value = "/verifytpinotp")
    public Mono<VerifyOtpResp> verifyTpinOtp(@Valid @RequestBody VerifyOtpReq verifyOtpReq) {
        return loginRegistrationBFFQryService.verifyTpinOtp(verifyOtpReq);
    }

    @Operation(summary = "verify old login pin")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Verify Tpin Otp", content = {@Content(schema = @Schema(implementation = GenericResponse.class))}),
            @ApiResponse(responseCode = "400", description = "BAD Request", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Data not Found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Server not responded", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})})
    @PostMapping(value = "/verifyoldloginpin", produces = "application/json")
    public Mono<GenericResponse> verifyOldloginMpin(@Valid @RequestBody VerifyOldLoginMPin verifyOldLoginMPin) {
        return loginRegistrationBFFQryService.verifyOldloginMpin(verifyOldLoginMPin);
    }

    @Operation(summary = "Get All Knowledge Questions")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Verify Tpin Otp", content = {@Content(schema = @Schema(implementation = KnowledgeQuestionsResp.class))}),
            @ApiResponse(responseCode = "400", description = "BAD Request", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Data not Found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Server not responded", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})})
    @PostMapping(value = "/get-all-knowledge-questions")
    public Mono<KnowledgeQuestionsResp> getAllKnowledgeQuestions() {
        return loginRegistrationBFFQryService.getAllKnowledgeQuestions();
    }

    @Operation(summary = "CheckMbRegistration")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Verify Tpin Otp", content = {@Content(schema = @Schema(implementation = CheckMbRegistrationResp.class))}),
            @ApiResponse(responseCode = "400", description = "BAD Request", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Data not Found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Server not responded", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})})
    @PostMapping(value = "/checkMbRegistration", produces = "application/json")
    public Mono<CheckMbRegistrationResp> checkMbRegistration(@RequestBody CheckMbRegistrationReq checkMbRegistrationReq) {
        return loginRegistrationBFFQryService.checkMbRegistration(checkMbRegistrationReq);
    }

    @Operation(summary = "Verify TPIN")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "Verify Tpin Otp", content = {@Content(schema = @Schema(implementation = GenericResponse.class))}),
            @ApiResponse(responseCode = "400", description = "BAD Request", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Data not Found", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Server not responded", content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})})
    @PostMapping(value = "/verifytpin", produces = "application/json")
    public Mono<GenericResponse> verifyTpin(@Valid @RequestBody VerifyTpin verifyTpin) {
        return loginRegistrationBFFQryService.verifyTpin(verifyTpin);
    }
}
     

