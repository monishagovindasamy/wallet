package com.leovegas.wallet.transformer;

import com.leovegas.wallet.entity.TransactionEntity;
import com.leovegas.wallet.model.TransactionModel;

/**
 * The type Transaction transformer.
 */
public class TransactionTransformer {

    /**
     * Transform to entity transaction entity.
     *
     * @param transaction the transaction
     * @return the transaction entity
     */
    public static TransactionEntity transformToEntity(TransactionModel transaction) {
        return TransactionEntity.builder()
                .id(transaction.getId())
                .description(transaction.getDescription())
                .amount(transaction.getAmount())
                .createdAt(transaction.getCreatedAt())
                .type(transaction.getType())
                .playerId(transaction.getPlayerId())
                .build();
    }

    /**
     * Transform to model transaction model.
     *
     * @param transaction the transaction
     * @return the transaction model
     */
    public static TransactionModel transformToModel(TransactionEntity transaction) {
        return TransactionModel.builder()
                .id(transaction.getId())
                .description(transaction.getDescription())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .playerId(transaction.getPlayerId())
                .createdAt(transaction.getCreatedAt())
                .build();
    }
}
