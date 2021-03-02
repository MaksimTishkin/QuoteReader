package com.epam.tishkin.exception;

public class NonExistentQuoteException extends Throwable {

    public NonExistentQuoteException(String message) {
        super(message);
    }
}
