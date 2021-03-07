package com.jorgehuang.budgetservice.domain;

import lombok.*;

import java.sql.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private String category;

    @Getter
    @Setter
    private Double amount;

    @Getter
    @Setter
    private Date date;

    @Getter
    @Setter
    private Integer accountId;

    @Getter
    @Setter
    private String accountName;
}
