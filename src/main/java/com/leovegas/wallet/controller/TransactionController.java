package com.leovegas.wallet.controller;

import com.leovegas.wallet.model.TransactionModel;
import com.leovegas.wallet.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * The type Transaction controller.
 */
@RestController
@RequestMapping("transaction")
public class TransactionController {

    private final TransactionService transactionService;

    /**
     * Instantiates a new Transaction controller.
     *
     * @param transactionService the transaction service
     */
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Gets all transactions by player.
     *
     * @param playerId the player id
     * @return the all transactions by player
     */
    @GetMapping("{playerId}")
    public List<TransactionModel> getAllTransactionsByPlayer(@PathVariable Long playerId) {
        return transactionService.getAllTransactionsByPlayer(playerId);
    }

    /**
     * Create credit transaction.
     *
     * @param transaction the transaction
     */
    @PostMapping("credit")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCreditTransaction(@Valid @RequestBody TransactionModel transaction) {
        transactionService.createCreditTransaction(transaction);
    }

    /**
     * Create debit transaction.
     *
     * @param transaction the transaction
     */
    @PostMapping("debit")
    @ResponseStatus(HttpStatus.CREATED)
    public void createDebitTransaction(@Valid @RequestBody TransactionModel transaction) {
        transactionService.createDebitTransaction(transaction);
    }
}
