package net.javaguides.banking.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.banking.dto.AccountDto;
import net.javaguides.banking.service.AccountService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController // makes this class spring mvc rest controller class
//Used to create RESTful APIs and return data like JSON or XML instead of a view (HTML)
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;
    
    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    //-----------------------------------------------------------------------------------------------------------------------------
    //Add account REST API
    //first create a method 
    //make that method as rest api using spring annotation
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<AccountDto>(accountService.createAccount(accountDto),HttpStatus.CREATED);
    }
    //@RequestBody maps request body(Json) to the accountDto(java object) and converts json to java object
    //addAccount method calls accountService.createAccount method
    //createAccount method returns savedAccount
    //-----------------------------------------------------------------------------------------------------------------------------
    //get account by restAPI
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id) {
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto); 
    }
    //-----------------------------------------------------------------------------------------------------------------------------
    //deposit REST API
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String,Double> request){

        Double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(id,request.get("amount"));
        return ResponseEntity.ok(accountDto);
    }
    //-----------------------------------------------------------------------------------------------------------------------------
    //withdraw REST API
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String,Double> request){
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id ,request.get("amount"));
        return ResponseEntity.ok(accountDto);
    }
    //-----------------------------------------------------------------------------------------------------------------------
    //Get all accounts REST API
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
    List<AccountDto> accounts = accountService.getAllAccounts();
    return ResponseEntity.ok(accounts);
    }
    //----------------------------------------------------------------------------------------------------------------------
    //DeleteAccount REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("account is deleted successfully !");
    }
    
}
