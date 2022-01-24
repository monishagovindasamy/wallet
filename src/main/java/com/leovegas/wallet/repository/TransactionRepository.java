package com.leovegas.wallet.repository;

import com.leovegas.wallet.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Transaction repository.
 */
@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    /**
     * Find by player id list.
     *
     * @param playerId the player id
     * @return the list
     */
    List<TransactionEntity> findByPlayerId(Long playerId);
}
