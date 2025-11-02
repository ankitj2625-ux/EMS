package com.hsbc.banking.EMS.globalException.exception;

public class InvalidEmployeeDataException extends RuntimeException{
    public InvalidEmployeeDataException(String message) {
        super(message);
    }
}
