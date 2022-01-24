package com.leovegas.wallet.exception;

/**
 * The type Transaction already exists exception.
 */
public class TransactionAlreadyExistsException extends RuntimeException {

    /**
     * Instantiates a new Transaction already exists exception.
     *
     * @param error the error
     */
    public TransactionAlreadyExistsException(String error) {
        super(error);
    }
}
