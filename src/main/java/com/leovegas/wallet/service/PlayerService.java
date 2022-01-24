package com.leovegas.wallet.service;

import com.leovegas.wallet.model.AccountModel;
import com.leovegas.wallet.model.PlayerModel;

/**
 * The interface Player service.
 */
public interface PlayerService {

    /**
     * Gets balance by player.
     *
     * @param id the id
     * @return the balance by player
     */
    AccountModel getBalanceByPlayer(Long id);

    /**
     * Create player model.
     *
     * @param playerModel the player model
     * @return the player model
     */
    PlayerModel create(PlayerModel playerModel);
}
