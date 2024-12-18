package net.javaguides.banking.exception;

import java.time.LocalDateTime;
//since this is a record, not a class file, it gives boilerplate code by default
public record ErrorDetails(LocalDateTime timestamp, String message, String details, String errorCode) {

}
