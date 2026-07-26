package com.eazybytes.accounts.mapper;

import com.eazybytes.accounts.dto.AccountDto;
import com.eazybytes.accounts.entity.Accounts;

public class AccountsMapper {

    public static AccountDto mapToAccountDto(Accounts accounts, AccountDto accountDto) {
        accountDto.setAccountType(accounts.getAccountType());
        accountDto.setAccountNumber(accounts.getAccountNumber());
        accountDto.setBranchAddress(accounts.getBranchAddress());
        return accountDto;
    }

    public static Accounts mapToAccounts(AccountDto accountDto, Accounts accounts) {
        accounts.setAccountType(accountDto.getAccountType());
        accounts.setAccountNumber(accountDto.getAccountNumber());
        accounts.setBranchAddress(accountDto.getBranchAddress());
        return accounts;
    }
}
