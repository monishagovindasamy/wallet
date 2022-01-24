package com.leovegas.wallet.exception;

/**
 * The type Invalid transaction type exception.
 */
public class InvalidTransactionTypeException extends RuntimeException {

    /**
     * Instantiates a new Invalid transaction type exception.
     *
     * @param error the error
     */
    public InvalidTransactionTypeException(String error) {
        super(error);
    }
}
