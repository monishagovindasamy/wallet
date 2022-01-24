package com.leovegas.wallet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leovegas.wallet.model.TransactionModel;
import com.leovegas.wallet.model.TransactionType;
import com.leovegas.wallet.service.TransactionService;
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
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TransactionController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TransactionService transactionService;

    private TransactionModel creditTransactionModel;

    private TransactionModel debitTransactionModel;

    @BeforeEach
    public void setup() {
        debitTransactionModel = TransactionModel.builder()
                .id(1L)
                .playerId(1L)
                .amount(BigDecimal.valueOf(500))
                .type(TransactionType.DEBIT)
                .createdAt(ZonedDateTime.now())
                .build();
        creditTransactionModel = TransactionModel.builder()
                .id(1l).playerId(1L).amount(BigDecimal.valueOf(500))
                .type(TransactionType.CREDIT)
                .createdAt(ZonedDateTime.now())
                .build();

    }

    @Test
    public void testThatGetAllTransactionsByPlayerIdShouldReturnAllTransactions() throws Exception {
        when(transactionService.getAllTransactionsByPlayer(anyLong())).thenReturn(List.of(debitTransactionModel));
        mockMvc.perform(get("/transaction/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].amount").value(500))
                .andExpect(jsonPath("$.[0].type").value("DEBIT"));
    }

    @Test
    public void testThatCreateCreditTransactionShouldCreateTransaction() throws Exception {
        mockMvc.perform(post("/transaction/credit")
                .content(objectMapper.writeValueAsString(creditTransactionModel))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    public void testThatCreateDebitTransactionShouldCreateTransaction() throws Exception {
        mockMvc.perform(post("/transaction/debit")
                .content(objectMapper.writeValueAsString(debitTransactionModel))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
