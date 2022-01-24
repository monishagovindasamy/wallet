package com.leovegas.wallet.exception;

/**
 * The type Player not found exception.
 */
public class PlayerNotFoundException extends RuntimeException {

    /**
     * Instantiates a new Player not found exception.
     *
     * @param error the error
     */
    public PlayerNotFoundException(String error) {
        super(error);
    }
}
