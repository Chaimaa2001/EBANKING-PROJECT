package org.sid.ebanckingbackend.exception;

public class BankAccountNotFoundException extends Exception{
    public BankAccountNotFoundException(String s) {
        super(s);
    }
}
