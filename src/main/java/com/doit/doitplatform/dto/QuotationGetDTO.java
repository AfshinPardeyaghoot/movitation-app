package com.doit.doitplatform.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuotationGetDTO {
    private Long id;
    private String quote;
    private Boolean isLiked;
}
