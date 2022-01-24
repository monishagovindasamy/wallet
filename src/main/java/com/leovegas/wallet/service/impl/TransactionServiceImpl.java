package com.leovegas.wallet.service.impl;

import com.leovegas.wallet.entity.PlayerEntity;
import com.leovegas.wallet.exception.InSufficientBalanceException;
import com.leovegas.wallet.exception.InvalidTransactionTypeException;
import com.leovegas.wallet.exception.PlayerNotFoundException;
import com.leovegas.wallet.exception.TransactionAlreadyExistsException;
import com.leovegas.wallet.model.TransactionModel;
import com.leovegas.wallet.model.TransactionType;
import com.leovegas.wallet.repository.PlayerRepository;
import com.leovegas.wallet.repository.TransactionRepository;
import com.leovegas.wallet.service.TransactionService;
import com.leovegas.wallet.transformer.TransactionTransformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.leovegas.wallet.transformer.TransactionTransformer.transformToEntity;
import static java.util.Collections.emptyList;

/**
 * The type Transaction service.
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final PlayerRepository playerRepository;

    /**
     * Instantiates a new Transaction service.
     *
     * @param transactionRepository the transaction repository
     * @param playerRepository      the player repository
     */
    public TransactionServiceImpl(TransactionRepository transactionRepository, PlayerRepository playerRepository) {
        this.transactionRepository = transactionRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public List<TransactionModel> getAllTransactionsByPlayer(Long playerId) {
        var players = transactionRepository.findByPlayerId(playerId);
        if (players == null) {
            return emptyList();
        }
        return players.stream().map(TransactionTransformer::transformToModel).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void createCreditTransaction(TransactionModel transaction) {

        // Check Transaction Already exists
        checkTransactionAlreadyExists(transaction);

        // Check Transaction Type is valid
        if (transaction.getType() != TransactionType.CREDIT) {
            throw new InvalidTransactionTypeException("Transaction with credit type only allowed");
        }
        var player = getPlayer(transaction.getPlayerId());
        var account = player.getAccount();
        account.setBalance(account.getBalance().add(transaction.getAmount()));
        account.setModifiedAt(transaction.getCreatedAt());
        playerRepository.save(player);
        transactionRepository.save(transformToEntity(transaction));
    }

    @Override
    @Transactional
    public void createDebitTransaction(TransactionModel transaction) {
        // Check Transaction Already exists
        checkTransactionAlreadyExists(transaction);

        // Check Transaction Type is valid
        if (transaction.getType() != TransactionType.DEBIT) {
            throw new InvalidTransactionTypeException("Transaction with debit type only allowed");
        }

        var player = getPlayer(transaction.getPlayerId());
        // Check Balance available or not
        if (!isBalanceAvailable(transaction, player)) {
            throw new InSufficientBalanceException("Balance is not sufficient to make transaction");
        }
        var account = player.getAccount();
        account.setBalance(account.getBalance().subtract(transaction.getAmount().abs()));
        account.setModifiedAt(transaction.getCreatedAt());
        playerRepository.save(player);
        transactionRepository.save(transformToEntity(transaction));
    }

    private void checkTransactionAlreadyExists(TransactionModel transaction) {
        if (transactionRepository.existsById(transaction.getId())) {
            throw new TransactionAlreadyExistsException("Transaction with id " + transaction.getId() + " already exists");
        }
    }

    private boolean isBalanceAvailable(TransactionModel transaction, PlayerEntity player) {
        return player.getAccount().getBalance().compareTo(transaction.getAmount().abs()) >= 0;
    }

    private PlayerEntity getPlayer(Long id) {
        var playerOptional = playerRepository.findById(id);
        return playerOptional.orElseThrow(() -> new PlayerNotFoundException("Player with id " + id + " not found"));
    }
}
