package com.doit.doitplatform.repository;

import com.doit.doitplatform.base.repository.AbstractRepository;
import com.doit.doitplatform.model.Category;
import com.doit.doitplatform.model.Quotation;
import com.doit.doitplatform.model.QuotationCategory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuotationCategoryRepository extends AbstractRepository<QuotationCategory, Long> {
    Optional<QuotationCategory> findByCategoryAndQuotation(Category category, Quotation quotation);
}
