package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

//@Data
//@AllArgsConstructor
@Schema(
        name = "Response",
        description = "Schema to hold successful response information"
)// This annotation use for open api documentation. To show the schema name
public class ResponseDto {
	
	@Schema(
            description = "Status code in the response"
    )// This annotation use for open api documentation. To show the field information and example
    private String statusCode;

    @Schema(
            description = "Status message in the response"
    )// This annotation use for open api documentation. To show the field information and example
    private String statusMsg;

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	public ResponseDto(String statusCode, String statusMsg) {
		super();
		this.statusCode = statusCode;
		this.statusMsg = statusMsg;
	}

	@Override
	public String toString() {
		return "ResponseDto [statusCode=" + statusCode + ", statusMsg=" + statusMsg + "]";
	}
    
    
}
