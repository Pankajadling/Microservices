package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
//@AllArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error response information"
)// This annotation use for open api documentation. To show the schema name
public class ErrorResponseDto {
	
  	@Schema(
            description = "API path invoked by client"
    )// This annotation use for open api documentation. To show the field information and example
    private String apiPath;

    @Schema(
            description = "Error code representing the error happened"
    )// This annotation use for open api documentation. To show the field information and example
    private HttpStatus httpStatus;

    @Schema(
            description = "Error message representing the error happened"
    )// This annotation use for open api documentation. To show the field information and example
    private String errorMessage;

    @Schema(
            description = "Time representing when the error happened"
    )// This annotation use for open api documentation. To show the field information and example
    private LocalDateTime errorTime;
    
    

	public ErrorResponseDto(String apiPath, HttpStatus httpStatus, String errorMessage, LocalDateTime errorTime) {
		super();
		this.apiPath = apiPath;
		this.httpStatus = httpStatus;
		this.errorMessage = errorMessage;
		this.errorTime = errorTime;
	}

	public String getApiPath() {
		return apiPath;
	}

	public void setApiPath(String apiPath) {
		this.apiPath = apiPath;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public LocalDateTime getErrorTime() {
		return errorTime;
	}

	public void setErrorTime(LocalDateTime errorTime) {
		this.errorTime = errorTime;
	}

	@Override
	public String toString() {
		return "ErrorResponseDto [apiPath=" + apiPath + ", httpStatus=" + httpStatus + ", errorMessage=" + errorMessage
				+ ", errorTime=" + errorTime + "]";
	}
        
}
