package org.sid.ebanckingbackend.exception;

public class BalanceNotSufficentException extends Exception {
    public BalanceNotSufficentException(String balanceNotSufficient) {
        super(balanceNotSufficient);

    }
}
