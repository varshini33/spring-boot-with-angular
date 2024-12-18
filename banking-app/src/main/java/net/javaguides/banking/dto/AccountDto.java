package net.javaguides.banking.dto;

// import lombok.AllArgsConstructor;
// import lombok.Data;

// @Data //generates boilerplate code for class AccountDto
// @AllArgsConstructor //generates a constructor requiring argument for every field in the annotated class
// public class AccountDto {
//     private Long id;
//     private String accountHolderName;
//     private double balance;
// }

public record AccountDto(Long id, String accountHolderName, double balance){

}