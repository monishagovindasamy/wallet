package com.leovegas.wallet.exception.handler;

import com.leovegas.wallet.exception.InvalidTransactionTypeException;
import com.leovegas.wallet.exception.PlayerNotFoundException;
import com.leovegas.wallet.exception.TransactionAlreadyExistsException;
import com.leovegas.wallet.exception.model.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Wallet exception handler.
 */
@Slf4j
@RestControllerAdvice
public class WalletExceptionHandler {

    /**
     * Handle player not found exception error.
     *
     * @param exception the exception
     * @return the error
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PlayerNotFoundException.class)
    public Error handlePlayerNotFoundException(PlayerNotFoundException exception) {
        return new Error(exception.getMessage());
    }

    /**
     * Handle transaction already exists exception error.
     *
     * @param exception the exception
     * @return the error
     */
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(TransactionAlreadyExistsException.class)
    public Error handleTransactionAlreadyExistsException(TransactionAlreadyExistsException exception) {
        return new Error(exception.getMessage());
    }

    /**
     * Handle invalid transaction type exception error.
     *
     * @param exception the exception
     * @return the error
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidTransactionTypeException.class)
    public Error handleInvalidTransactionTypeException(InvalidTransactionTypeException exception) {
        return new Error(exception.getMessage());
    }

    /**
     * Handle validation exceptions map.
     *
     * @param ex the ex
     * @return the map
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
