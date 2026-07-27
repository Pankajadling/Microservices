package com.eazybytes.loans.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "loans")
//@Getter @Setter
public class LoanContactInfoData {
    private String message;
    private Map<String,String> contactDetails;
    private List<String> onCallSupport;
    
	public LoanContactInfoData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoanContactInfoData(String message, Map<String, String> contactDetails, List<String> onCallSupport) {
		super();
		this.message = message;
		this.contactDetails = contactDetails;
		this.onCallSupport = onCallSupport;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, String> getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(Map<String, String> contactDetails) {
		this.contactDetails = contactDetails;
	}

	public List<String> getOnCallSupport() {
		return onCallSupport;
	}

	public void setOnCallSupport(List<String> onCallSupport) {
		this.onCallSupport = onCallSupport;
	}

	@Override
	public String toString() {
		return "LoanContactInfoData [message=" + message + ", contactDetails=" + contactDetails + ", onCallSupport="
				+ onCallSupport + "]";
	}
    
    
}
// When used the record then it will automatically assign values to the field at the statrup and it's a final then
// you can not change it runtimes.
//public record LoanContactInfoData(String message, Map<String,String> contactDetails, List<String> onCallSupport) {
//
//}
