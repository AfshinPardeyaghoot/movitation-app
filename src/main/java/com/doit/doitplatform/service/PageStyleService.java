package com.doit.doitplatform.service;

import com.doit.doitplatform.base.service.BaseServiceImpl;
import com.doit.doitplatform.model.PageStyle;
import com.doit.doitplatform.repository.PageStyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageStyleService extends BaseServiceImpl<PageStyle, Long, PageStyleRepository> {

    private final PageStyleRepository repository;

    @Autowired
    public PageStyleService(PageStyleRepository abstractRepository) {
        super(abstractRepository);
        this.repository = abstractRepository;
    }


}
