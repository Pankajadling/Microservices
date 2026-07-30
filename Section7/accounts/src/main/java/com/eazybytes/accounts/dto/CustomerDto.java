package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

//@Data
@Schema(
        name = "Customer",
        description = "Schema to hold Customer information"
)// This annotation use for open api documentation. To show the schema name
public class CustomerDto {

    @NotEmpty(message = "Name can not be null or empty")
    @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30")
    @Schema(
            description = "Name of the Customer", example = "Eazy Bytes"
    )// This annotation use for open api documentation. To show the field information and example
    private String name;

    @NotEmpty(message = "Email address can not be null or empty")
    @Email(message = "Email address should be valid value")
    @Schema(
            description = "Email address of the Customer", example = "abc@gmail.com"
    )// This annotation use for open api documentation. To show the field information and example
    private String email;

    @Pattern(regexp = "(^[0-9]{10}$)", message = "Mobile number must be 10 digit")
    @Schema(
            description = "Mobile number of the Customer", example = "9878767654"
    )// This annotation use for open api documentation. To show the field information and example
    private String mobileNumber;

    @Schema(
            description = "Account details of the Customer"
    )// This annotation use for open api documentation. To show the field information and example
    private AccountDto accountDto;

	public CustomerDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerDto(
			@NotEmpty(message = "Name can not be null or empty") @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30") String name,
			@NotEmpty(message = "Email address can not be null or empty") @Email(message = "Email address should be valid value") String email,
			@Pattern(regexp = "(^[0-9]{10}$)", message = "Mobile number must be 10 digit") String mobileNumber,
			AccountDto accountDto) {
		super();
		this.name = name;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.accountDto = accountDto;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public AccountDto getAccountDto() {
		return accountDto;
	}

	public void setAccountDto(AccountDto accountDto) {
		this.accountDto = accountDto;
	}

	@Override
	public String toString() {
		return "CustomerDto [name=" + name + ", email=" + email + ", mobileNumber=" + mobileNumber + ", accountDto="
				+ accountDto + "]";
	}
    
}
