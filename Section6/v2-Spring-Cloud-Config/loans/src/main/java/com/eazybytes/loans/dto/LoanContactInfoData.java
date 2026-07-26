package com.eazybytes.loans.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "loans")
@Getter @Setter
public class LoanContactInfoData {
    private String message;
    private Map<String,String> contactDetails;
    private List<String> onCallSupport;
}
// When used the record then it will automatically assign values to the field at the statrup and it's a final then
// you can not change it runtimes.
//public record LoanContactInfoData(String message, Map<String,String> contactDetails, List<String> onCallSupport) {
//
//}
