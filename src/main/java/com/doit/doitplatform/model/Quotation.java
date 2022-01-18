package com.doit.doitplatform.model;

import com.doit.doitplatform.base.model.BaseEntity;
import com.doit.doitplatform.dto.QuotationGetDTO;
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


    public QuotationGetDTO quotationGetDTO(List<Long> likedQuotations) {
        QuotationGetDTO getDTO = new QuotationGetDTO();
        getDTO.setQuote(quote);
        getDTO.setId(getId());
        if (likedQuotations.contains(getId()))
            getDTO.setIsLiked(true);
        else getDTO.setIsLiked(false);
        return getDTO;
    }
}
