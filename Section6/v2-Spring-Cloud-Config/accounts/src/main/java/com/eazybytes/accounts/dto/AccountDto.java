package com.eazybytes.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

//@Data
@Schema(
        name = "Account",
        description = "Schema to hold Account information"
)// This annotation use for open api documentation. To show the schema name
public class AccountDto {

    @NotEmpty(message = "Account number can not be null or empty")
    @Pattern(regexp = "(^[0-9]{10}$)", message = "Account number must be 10 digit")
    @Schema(
            description = "Account number of Eazy Bank Account", example = "1232123432"
    )// This annotation use for open api documentation. To show the field information and example
    private Long accountNumber;

    @NotEmpty(message = "Account type can not be null or empty")
    @Schema(
            description = "Account type of Eazy Bank Account", example = "Savings"
    )// This annotation use for open api documentation. To show the field information and example
    private String accountType;

    @NotEmpty(message = "Branch address  can not be null or empty")
    @Schema(
            description = "Eazy Bank branch address", example = "123 New York, Sector 321, Pin-423123"
    )// This annotation use for open api documentation. To show the field information and example
    private String branchAddress;

    
	public AccountDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountDto(
			@NotEmpty(message = "Account number can not be null or empty") @Pattern(regexp = "(^[0-9]{10}$)", message = "Account number must be 10 digit") Long accountNumber,
			@NotEmpty(message = "Account type can not be null or empty") String accountType,
			@NotEmpty(message = "Branch address  can not be null or empty") String branchAddress) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.branchAddress = branchAddress;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	@Override
	public String toString() {
		return "AccountDto [accountNumber=" + accountNumber + ", accountType=" + accountType + ", branchAddress="
				+ branchAddress + "]";
	}
    
}
