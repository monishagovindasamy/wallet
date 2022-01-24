package com.leovegas.wallet.repository;

import com.leovegas.wallet.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Player repository.
 */
@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {
}
