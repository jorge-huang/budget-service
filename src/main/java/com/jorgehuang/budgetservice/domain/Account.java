package com.jorgehuang.budgetservice.domain;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String type;

    @Getter
    @Setter
    private Boolean enabled;
}
