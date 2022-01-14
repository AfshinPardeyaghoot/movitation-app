package com.doit.doitplatform.service;

import com.doit.doitplatform.base.service.BaseServiceImpl;
import com.doit.doitplatform.model.Quotation;
import com.doit.doitplatform.repository.QuotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuotationService extends BaseServiceImpl<Quotation, Long, QuotationRepository> {


    private final QuotationRepository repository;

    @Autowired
    public QuotationService(QuotationRepository abstractRepository) {
        super(abstractRepository);
        this.repository = abstractRepository;
    }
}
