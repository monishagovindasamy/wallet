package com.leovegas.wallet.service.impl;

import com.leovegas.wallet.entity.PlayerEntity;
import com.leovegas.wallet.exception.PlayerNotFoundException;
import com.leovegas.wallet.model.AccountModel;
import com.leovegas.wallet.model.PlayerModel;
import com.leovegas.wallet.repository.PlayerRepository;
import com.leovegas.wallet.service.PlayerService;
import com.leovegas.wallet.transformer.AccountTransformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.leovegas.wallet.transformer.PlayerTransformer.transformToEntity;
import static com.leovegas.wallet.transformer.PlayerTransformer.transformToModel;

/**
 * The type Player service.
 */
@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    /**
     * Instantiates a new Player service.
     *
     * @param playerRepository the player repository
     */
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    @Transactional
    public AccountModel getBalanceByPlayer(Long id) {
        var playerEntity = getPlayer(id);
        return AccountTransformer.transformToModel(playerEntity.getAccount());
    }

    @Override
    @Transactional
    public PlayerModel create(PlayerModel playerModel) {
        var player = playerRepository.save(transformToEntity(playerModel));
        return transformToModel(player);
    }

    private PlayerEntity getPlayer(Long id) {
        var playerOptional = playerRepository.findById(id);
        return playerOptional.orElseThrow(() -> new PlayerNotFoundException("Player with id " + id + " not found"));
    }
}
