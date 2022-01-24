package com.leovegas.wallet.exception;

/**
 * The type In sufficient balance exception.
 */
public class InSufficientBalanceException extends RuntimeException {

    /**
     * Instantiates a new In sufficient balance exception.
     *
     * @param error the error
     */
    public InSufficientBalanceException(String error) {
        super(error);
    }
}
