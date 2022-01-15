package com.doit.doitplatform.model;

import com.doit.doitplatform.base.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageStyle extends BaseEntity<Long> {
    private String backgroundImage;
    private String fontColor;
}
