package com.doit.doitplatform.repository;

import com.doit.doitplatform.base.repository.AbstractRepository;
import com.doit.doitplatform.model.QuotationCategory;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotationCategoryRepository extends AbstractRepository<QuotationCategory, Long> {
}
