package com.doit.doitplatform.model;

import com.doit.doitplatform.base.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "page_style")
public class PageStyle extends BaseEntity<Long> {
    private String backgroundImage;
    private String fontColor;
    private String fontFile;
}
