package com.leovegas.wallet.service;

import com.leovegas.wallet.entity.AccountEntity;
import com.leovegas.wallet.entity.PlayerEntity;
import com.leovegas.wallet.entity.TransactionEntity;
import com.leovegas.wallet.exception.InvalidTransactionTypeException;
import com.leovegas.wallet.model.TransactionModel;
import com.leovegas.wallet.model.TransactionType;
import com.leovegas.wallet.repository.PlayerRepository;
import com.leovegas.wallet.repository.TransactionRepository;
import com.leovegas.wallet.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    private TransactionServiceImpl transactionService;

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private TransactionRepository transactionRepository;

    private TransactionModel creditTransactionModel;

    private TransactionModel debitTransactionModel;

    private TransactionEntity transactionEntity;

    private PlayerEntity playerEntity;


    @BeforeEach
    public void setup() {
        transactionService = new TransactionServiceImpl(transactionRepository, playerRepository);
        creditTransactionModel = TransactionModel.builder()
                .id(1L).amount(BigDecimal.valueOf(400))
                .playerId(1L).type(TransactionType.CREDIT)
                .description("Transaction")
                .createdAt(ZonedDateTime.now())
                .build();
        debitTransactionModel = TransactionModel.builder()
                .id(1L)
                .amount(BigDecimal.valueOf(400))
                .playerId(1L).type(TransactionType.DEBIT)
                .description("Transaction")
                .createdAt(ZonedDateTime.now())
                .build();
        transactionEntity = TransactionEntity.builder()
                .id(1L)
                .amount(BigDecimal.valueOf(400))
                .playerId(1L)
                .type(TransactionType.CREDIT)
                .description("Transaction")
                .createdAt(ZonedDateTime.now())
                .build();
        playerEntity = PlayerEntity.builder()
                .id(1L)
                .account(AccountEntity.builder().id(1L).balance(BigDecimal.valueOf(500)).build())
                .address("Stockholm")
                .createdAt(ZonedDateTime.now())
                .build();
    }

    @Test
    public void testThatGetAllTransactionsByPlayerShouldReturnListOfTransactions() {
        when(transactionRepository.findByPlayerId(any())).thenReturn(Collections.singletonList(transactionEntity));

        var transactionsByPlayer = transactionService.getAllTransactionsByPlayer(1L);

        assertEquals(BigDecimal.valueOf(400), transactionsByPlayer.get(0).getAmount());
        assertEquals("Transaction", transactionsByPlayer.get(0).getDescription());

    }

    @Test
    public void testThatCreateCreditTransactionShouldThrowExceptionForNonCreditTransactions() {

        var exception = assertThrows(InvalidTransactionTypeException.class,
                () -> transactionService.createCreditTransaction(debitTransactionModel));

        String expectedMessage = "Transaction with credit type only allowed";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testThatCreateCreditTransactionShouldCreateCreditTransactions() {
        when(playerRepository.findById(any())).thenReturn(Optional.of(playerEntity));

        transactionService.createCreditTransaction(creditTransactionModel);

        verify(playerRepository, times(1)).save(any());
        verify(transactionRepository, times(1)).save(any());
    }

    @Test
    public void testThatCreateDebitTransactionShouldCreateDebitTransactions() {
        when(playerRepository.findById(any())).thenReturn(Optional.of(playerEntity));

        transactionService.createDebitTransaction(debitTransactionModel);

        verify(playerRepository, times(1)).save(any());
        verify(transactionRepository, times(1)).save(any());
    }

    @Test
    public void testThatCreateDebitTransactionShouldThrowExceptionForNonDebitTransactions() {
        var exception = assertThrows(InvalidTransactionTypeException.class,
                () -> transactionService.createDebitTransaction(creditTransactionModel));

        String expectedMessage = "Transaction with debit type only allowed";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
