package net.javaguides.banking.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.javaguides.banking.dto.AccountDto;
import net.javaguides.banking.entity.Account;
import net.javaguides.banking.exception.AccountException;
import net.javaguides.banking.mapper.AccountMapper;
import net.javaguides.banking.service.AccountService;
import net.javaguides.banking.repository.AccountRepository;

@Service //to automatically create spring bean
public class AccountServiceImpl implements AccountService{

    //injecting dependencies
    private AccountRepository accountRepository;

    //constructor for the class AcountServiceImpl
    @Autowired // to automatically inject dependencies
    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }
    //------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }
    //------------------------------------------------------------------------------------------------------------------------------------
    @Override 
    public AccountDto getAccountById(Long id){
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountException("Account does not exists"));
        return AccountMapper.mapToAccountDto(account);
    }
    //-------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public AccountDto deposit(Long id, double amount){
        //check if the account with given id exists or throw exception
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountException("account doesnt exists"));
        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }
    //-------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public AccountDto withdraw(Long id, double amount){
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountException("acount doesnt exists"));
        //check for sufficient balance
        if(account.getBalance() < amount){
            throw new RuntimeException("insufficient amount");
        }
        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }
    //-------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map((account) -> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
    }
    //-------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public void deleteAccount(Long id){
        Account account = accountRepository.findById(id).orElseThrow(() -> new AccountException("account doesnot exists"));
        accountRepository.deleteById(id);
    }
}
