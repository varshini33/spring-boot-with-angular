package net.javaguides.banking.mapper;

import net.javaguides.banking.dto.AccountDto;
import net.javaguides.banking.entity.Account;

public class AccountMapper {
    
    // dto to jpa entity
    public static Account mapToAccount(AccountDto accountDto){
        Account account = new Account(
            accountDto.id(),
            accountDto.accountHolderName(),
            accountDto.balance()
        );

        return account;
    }
    //------------------------------------------------------------------------------------------------------------------------------
    //jpa entity to dto
    public static AccountDto mapToAccountDto(Account account){
        AccountDto accountDto = new  AccountDto(
            account.getId(),
            account.getAccount(), //check again for getAccountHolderName()
            account.getBalance()
        );
        return accountDto;
    }
}
