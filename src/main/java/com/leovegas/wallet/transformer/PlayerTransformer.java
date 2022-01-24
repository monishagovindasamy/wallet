package com.leovegas.wallet.transformer;

import com.leovegas.wallet.entity.PlayerEntity;
import com.leovegas.wallet.model.PlayerModel;

/**
 * The type Player transformer.
 */
public class PlayerTransformer {

    /**
     * Transform to entity player entity.
     *
     * @param player the player
     * @return the player entity
     */
    public static PlayerEntity transformToEntity(PlayerModel player) {
        return PlayerEntity.builder()
                .id(player.getId())
                .name(player.getName())
                .address(player.getAddress())
                .createdAt(player.getCreatedAt())
                .modifiedAt(player.getModifiedAt())
                .account(AccountTransformer.transformToEntity(player.getAccount()))
                .build();
    }

    /**
     * Transform to model player model.
     *
     * @param player the player
     * @return the player model
     */
    public static PlayerModel transformToModel(PlayerEntity player) {
        return PlayerModel.builder()
                .id(player.getId())
                .name(player.getName())
                .address(player.getAddress())
                .createdAt(player.getCreatedAt())
                .modifiedAt(player.getModifiedAt())
                .account(AccountTransformer.transformToModel(player.getAccount()))
                .build();
    }
}
