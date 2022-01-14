package com.doit.doitplatform.model;

import com.doit.doitplatform.base.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "quotation")
public class Quotation extends BaseEntity<Long> {

    @Column(name = "qoute")
    private String quote;

    @OneToMany(mappedBy = "quotation")
    private List<QuotationCategory> categories;
}
