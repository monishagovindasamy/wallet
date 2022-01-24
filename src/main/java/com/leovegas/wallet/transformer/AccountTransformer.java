package com.leovegas.wallet.transformer;

import com.leovegas.wallet.entity.AccountEntity;
import com.leovegas.wallet.model.AccountModel;

/**
 * The type Account transformer.
 */
public class AccountTransformer {

    /**
     * Transform to model account model.
     *
     * @param account the account
     * @return the account model
     */
    public static AccountModel transformToModel(AccountEntity account) {
        return AccountModel
                .builder()
                .id(account.getId())
                .balance(account.getBalance())
                .modifiedAt(account.getModifiedAt())
                .build();
    }

    /**
     * Transform to entity account entity.
     *
     * @param account the account
     * @return the account entity
     */
    public static AccountEntity transformToEntity(AccountModel account) {
        return AccountEntity
                .builder()
                .id(account.getId())
                .balance(account.getBalance())
                .modifiedAt(account.getModifiedAt())
                .build();
    }
}
