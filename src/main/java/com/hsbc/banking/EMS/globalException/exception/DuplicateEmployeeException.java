package com.hsbc.banking.EMS.globalException.exception;

public class DuplicateEmployeeException extends RuntimeException{
    public DuplicateEmployeeException(String message) {
        super(message);
    }
}
