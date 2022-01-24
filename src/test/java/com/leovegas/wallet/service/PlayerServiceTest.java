package com.leovegas.wallet.service;

import com.leovegas.wallet.entity.AccountEntity;
import com.leovegas.wallet.entity.PlayerEntity;
import com.leovegas.wallet.model.AccountModel;
import com.leovegas.wallet.model.PlayerModel;
import com.leovegas.wallet.repository.PlayerRepository;
import com.leovegas.wallet.service.impl.PlayerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    private PlayerServiceImpl playerService;

    @Mock
    private PlayerRepository playerRepository;

    private PlayerModel playerModel;

    private PlayerEntity playerEntity;


    @BeforeEach
    public void setup() {
        playerService = new PlayerServiceImpl(playerRepository);
        AccountModel accountModel = AccountModel.builder()
                .id(1L)
                .balance(BigDecimal.valueOf(250))
                .modifiedAt(ZonedDateTime.now())
                .build();
        playerModel = PlayerModel.builder()
                .id(1L).account(accountModel)
                .address("Stockholm").name("Test")
                .createdAt(ZonedDateTime.now())
                .build();
        AccountEntity accountEntity = AccountEntity.builder()
                .id(1L)
                .balance(BigDecimal.valueOf(250))
                .modifiedAt(ZonedDateTime.now())
                .build();
        playerEntity = PlayerEntity.builder()
                .id(1L)
                .account(accountEntity)
                .name("Test")
                .address("Stockholm")
                .createdAt(ZonedDateTime.now())
                .build();
    }

    @Test
    public void testThatGetBalanceByPlayerShouldReturnBalance() {
        when(playerRepository.findById(any())).thenReturn(Optional.of(playerEntity));

        var balanceByPlayer = playerService.getBalanceByPlayer(1L);

        assertEquals(BigDecimal.valueOf(250), balanceByPlayer.getBalance());
    }

    @Test
    public void testThatCreateShouldCreatePlayerModel() {
        when(playerRepository.save(any())).thenReturn(playerEntity);

        var newPlayerModel = playerService.create(playerModel);

        assertEquals("Stockholm", newPlayerModel.getAddress());
        assertEquals(BigDecimal.valueOf(250), newPlayerModel.getAccount().getBalance());
    }
}
