package com.eazybytes.accounts.controller;


import com.eazybytes.accounts.constant.AcountsConstants;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ErrorResponseDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(
        name = "CRUD REST APIs for Accounts in EazyBank",
        description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH ANS DELETE acccount details."
)// Use this annotation for open api controller tagging. Provide some custom information for user-friendly application
@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class AccountController {

    @Autowired
    private IAccountService iAccountService;

    @Operation(
            summary = "Hello Account REST API",
            description = "REST API for Hello Account inside EazyBank"
    )// Use for open API documentation. To summarize the operation
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )// Use for open API documentation. Bcoz this error happens into the global exception class and this is
                    // not scan that why do it manually
            )
    })// Use for open API documentation. Show the api response code & msg.
    @GetMapping("/helloAccount")
    //    New Hello Account API
    public ResponseEntity<Map<String, String>> getHelloAccountMessage() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("message", "Hello Account V1 !!!!!!!"));
    }

    //  Old Hello Account API
    //    public String helloAccount() {
    //        return "Hello Hello Account V1 !!!!!!!";
    //    }

    @Operation(
            summary = "Create Customer & Account REST API",
            description = "REST API to create new Customer & Account inside EazyBank"
    )// Use for open API documentation. To summarize the operation
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )// Use for open API documentation. Bcoz this error happens into the global exception class and this is
                    // not scan that why do it manually
            )
    })// Use for open API documentation. Show the api response code & msg.
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        iAccountService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(AcountsConstants.STATUS_201, AcountsConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Customer & Account Details REST API",
            description = "REST API to fetch Customer & Account details based on a mobile number inside EazyBank"
    )// Use for open API documentation. To summarize the operation
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )// Use for open API documentation. Bcoz this error happens into the global exception class and this is
                    // not scan that why do it manually
            )}
    )// Use for open API documentation. Show the api response code & msg.
    @GetMapping("fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(@RequestParam
                                                           @Pattern(regexp = "(^[0-9]{10}$)", message = "Mobile number must be 10 digit")
                                                           String mobileNumber) {

        CustomerDto customerDto = iAccountService.fetchAccountDetails(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @Operation(
            summary = "Update Customer & Account Details REST API",
            description = "REST API to update Customer & Account details based on a account number EazyBank"
    )// Use for open API documentation. To summarize the operation
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Exception Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )// Use for open API documentation. Bcoz this error happens into the global exception class and this is
                    // not scan that why do it manually
            )
    })// Use for open API documentation. Show the api response code & msg.
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = iAccountService.updateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AcountsConstants.STATUS_200, AcountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AcountsConstants.STATUS_417, AcountsConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete Customer & Account Details REST API",
            description = "REST API to delete Customer & Account details based on a account number EazyBank"
    )// Use for open API documentation. To summarize the operation
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Exception Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )// Use for open API documentation. Bcoz this error happens into the global exception class and this is
                    // not scan that why do it manually
            )
    })// Use for open API documentation. Show the api response code & msg.
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam
                                                            @Pattern(regexp = "(^[0-9]{10}$)", message = "Mobile number must be 10 digit")
                                                            String mobileNumber) {
        boolean isDeleted = iAccountService.deleteAccountDetails(mobileNumber);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AcountsConstants.STATUS_200, AcountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AcountsConstants.STATUS_417, AcountsConstants.MESSAGE_417_DELETE));
        }
    }
}
