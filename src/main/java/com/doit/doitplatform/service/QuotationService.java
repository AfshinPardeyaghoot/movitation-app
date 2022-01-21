package com.doit.doitplatform.service;

import com.doit.doitplatform.base.service.BaseServiceImpl;
import com.doit.doitplatform.model.Category;
import com.doit.doitplatform.model.Quotation;
import com.doit.doitplatform.repository.QuotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuotationService extends BaseServiceImpl<Quotation, Long, QuotationRepository> {


    private final QuotationRepository repository;

    @Autowired
    public QuotationService(QuotationRepository abstractRepository) {
        super(abstractRepository);
        this.repository = abstractRepository;
    }

    public boolean isTableEmpty() {
        return repository.count() == 0;
    }

    public List<Quotation> findAllByCategory(Category category) {
        return repository.findAllByCategory(category);
    }

    public Quotation create(String quotation) {
        Quotation quote = new Quotation();
        quote.setQuote(quotation);
        quote.setIsDeleted(false);
        return save(quote);
    }
}
