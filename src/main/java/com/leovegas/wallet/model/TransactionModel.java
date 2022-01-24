package com.leovegas.wallet.model;

import lombok.Builder;
import lombok.Data;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@Value
@Builder
@Jacksonized
public class TransactionModel {

    @NotNull(message = "Transaction id cannot be null")
    Long id;

    String description;

    TransactionType type;

    BigDecimal amount;

    @Builder.Default
    ZonedDateTime createdAt = ZonedDateTime.now();

    Long playerId;
}
