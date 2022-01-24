package com.leovegas.wallet.service;

import com.leovegas.wallet.model.TransactionModel;

import java.util.List;

/**
 * The interface Transaction service.
 */
public interface TransactionService {
    /**
     * Create credit transaction.
     *
     * @param transaction the transaction
     */
    void createCreditTransaction(TransactionModel transaction);

    /**
     * Create debit transaction.
     *
     * @param transaction the transaction
     */
    void createDebitTransaction(TransactionModel transaction);

    /**
     * Gets all transactions by player.
     *
     * @param playerId the player id
     * @return the all transactions by player
     */
    List<TransactionModel> getAllTransactionsByPlayer(Long playerId);
}
