package com.leovegas.wallet.model;

import lombok.Builder;
import lombok.Data;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.ZonedDateTime;

@Data
@Value
@Builder
@Jacksonized
public class PlayerModel {

    @Builder.Default
    Long id = -1L;

    String name;

    String address;

    @Builder.Default
    ZonedDateTime createdAt = ZonedDateTime.now();

    @Builder.Default
    ZonedDateTime modifiedAt = null;

    AccountModel account;
}
