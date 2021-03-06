package com.jorgehuang.budgetservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

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
