package com.leovegas.wallet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leovegas.wallet.model.AccountModel;
import com.leovegas.wallet.model.PlayerModel;
import com.leovegas.wallet.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PlayerController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PlayerService playerService;

    private PlayerModel playerModel;

    private AccountModel accountModel;

    @BeforeEach
    public void setup() {
        accountModel = AccountModel.builder()
                .id(1L)
                .balance(BigDecimal.valueOf(50))
                .modifiedAt(ZonedDateTime.now())
                .build();

        playerModel = PlayerModel.builder()
                .id(1L)
                .name("TestName")
                .account(accountModel)
                .address("Stockholm")
                .createdAt(ZonedDateTime.now()).build();
    }

    @Test
    public void testThatGetBalanceByPlayerIdShouldReturnBalance() throws Exception {
        when(playerService.getBalanceByPlayer(anyLong())).thenReturn(accountModel);
        mockMvc.perform(get("/player/1/balance")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value(50));
    }

    @Test
    public void testThatCreatePlayerModelShouldCreatePlayer() throws Exception {
        when(playerService.create(any(PlayerModel.class))).thenReturn(playerModel);
        mockMvc.perform(post("/player")
                .content(objectMapper.writeValueAsString(playerModel))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.account.balance").value(50))
                .andExpect(jsonPath("$.name").value("TestName"));
    }
}
