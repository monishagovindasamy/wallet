package com.leovegas.wallet.model;

import lombok.Builder;
import lombok.Data;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@Value
@Builder
@Jacksonized
public class AccountModel {

    @Builder.Default
    Long id = -1L;

    BigDecimal balance;

    @Builder.Default
    ZonedDateTime modifiedAt = null;
}
