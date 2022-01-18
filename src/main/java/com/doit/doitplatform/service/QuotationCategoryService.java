package com.doit.doitplatform.service;

import com.doit.doitplatform.base.service.BaseServiceImpl;
import com.doit.doitplatform.exception.NotFoundException;
import com.doit.doitplatform.model.Category;
import com.doit.doitplatform.model.Quotation;
import com.doit.doitplatform.model.QuotationCategory;
import com.doit.doitplatform.repository.QuotationCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuotationCategoryService extends BaseServiceImpl<QuotationCategory, Long, QuotationCategoryRepository> {

    private QuotationCategoryRepository repository;

    @Autowired
    public QuotationCategoryService(QuotationCategoryRepository abstractRepository) {
        super(abstractRepository);
        this.repository = abstractRepository;
    }

    public boolean isTableEmpty() {
        return repository.count() == 0;
    }

    public QuotationCategory create(Quotation quotation, Category category) {
        QuotationCategory quotationCategory;
        if (repository.findByCategoryAndQuotation(category, quotation).isPresent()) {
            quotationCategory = repository.findByCategoryAndQuotation(category, quotation).get();
        } else {
            quotationCategory = new QuotationCategory();
            quotationCategory.setCategory(category);
            quotationCategory.setQuotation(quotation);
        }
        quotationCategory.setIsDeleted(false);
        return repository.save(quotationCategory);
    }

    public QuotationCategory findByQuotationAndCategory(Quotation quotation, Category category) {
        return repository.findByCategoryAndQuotation(category, quotation)
                .orElseThrow(() -> new NotFoundException(String.format("QuotationCategory by Quotation %d and Category %d not found", quotation.getId(), category.getId())));
    }
}
