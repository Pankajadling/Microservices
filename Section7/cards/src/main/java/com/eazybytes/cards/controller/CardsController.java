package com.eazybytes.cards.controller;

import com.eazybytes.cards.constants.CardsConstants;
import com.eazybytes.cards.dto.CardContactInfoData;
import com.eazybytes.cards.dto.CardsDto;
import com.eazybytes.cards.dto.ErrorResponseDto;
import com.eazybytes.cards.dto.ResponseDto;
import com.eazybytes.cards.service.ICardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Eazy Bytes
 */

@Tag(
        name = "CRUD REST APIs for Cards in EazyBank",
        description = "CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH AND DELETE card details"
)
@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
//@AllArgsConstructor
@Validated
public class CardsController {

    // @Autowired  // Optional
    private final ICardsService iCardsService;

    CardsController(ICardsService iCardsService){
        this.iCardsService=iCardsService;
    }

    // Reading configuration properties approaches are below
    // 1st approach
    @Value("${build.version}")
    private String buildVersion;

    // 2nd approach
    @Autowired
    private Environment environment; // Get environment variable

    // 3rd approach
    @Autowired
    private CardContactInfoData cardContactInfoData; // Get environment variable from properties or yml file


    @Operation(
            summary = "Hello Cards REST API",
            description = "REST API for Hello Cards inside EazyBank"
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
    @GetMapping("/helloCards")
    //    New Hello Cards API
    public ResponseEntity<Map<String, String>> getHelloCardsMessage() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("message", "Hello Cards V1 !!!!!!!"));
    }

    @Operation(
            summary = "Create Card REST API",
            description = "REST API to create new Card inside EazyBank"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createCard(@Valid @RequestParam
                                                      @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                      String mobileNumber) {
        iCardsService.createCard(mobileNumber);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Card Details REST API",
            description = "REST API to fetch card details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/fetch")
    public ResponseEntity<CardsDto> fetchCardDetails(@RequestParam
                                                               @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                               String mobileNumber) {
        CardsDto cardsDto = iCardsService.fetchCard(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
    }

    @Operation(
            summary = "Update Card Details REST API",
            description = "REST API to update card details based on a card number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
        })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCardDetails(@Valid @RequestBody CardsDto cardsDto) {
        boolean isUpdated = iCardsService.updateCard(cardsDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete Card Details REST API",
            description = "REST API to delete Card details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteCardDetails(@RequestParam
                                                                @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                                String mobileNumber) {
        boolean isDeleted = iCardsService.deleteCard(mobileNumber);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_DELETE));
        }
    }

    @Operation(
            summary = "Build information REST API",
            description = "REST API for build information inside EazyBank"
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
    @GetMapping("/build-info")
    //    New Hello Account API
    public ResponseEntity<Map<String, String>> getBuildVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("build-version", buildVersion));
    }

    @Operation(
            summary = "Get java version REST API",
            description = "REST API for get java version inside EazyBank"
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
    @GetMapping("/java-vervion")
    //    New Hello Account API
    public ResponseEntity<Map<String, String>> getJavaVersion() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Map.of("java-version", environment.getProperty("JAVA_HOME")));
    }

    @Operation(
            summary = "Get contact info REST API",
            description = "REST API for get contact info inside EazyBank"
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
    @GetMapping("/contact-info")
    //    New Hello Account API
    public ResponseEntity<CardContactInfoData> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardContactInfoData);
    }

}
