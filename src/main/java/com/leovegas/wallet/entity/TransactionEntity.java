package com.leovegas.wallet.entity;

import com.leovegas.wallet.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "transaction")
public class TransactionEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @Column(name = "player_id")
    private Long playerId;

}
