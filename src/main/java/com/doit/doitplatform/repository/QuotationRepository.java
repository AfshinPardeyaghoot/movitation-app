package com.doit.doitplatform.repository;

import com.doit.doitplatform.base.repository.AbstractRepository;
import com.doit.doitplatform.model.Category;
import com.doit.doitplatform.model.Quotation;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuotationRepository extends AbstractRepository<Quotation, Long> {

    @Query("select q from Quotation q inner join QuotationCategory qc on q = qc.quotation where qc.category =:category")
    List<Quotation> findAllByCategory(Category category);
}
