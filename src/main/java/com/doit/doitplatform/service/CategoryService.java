package com.doit.doitplatform.service;

import com.doit.doitplatform.base.service.BaseServiceImpl;
import com.doit.doitplatform.exception.NotFoundException;
import com.doit.doitplatform.model.Category;
import com.doit.doitplatform.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends BaseServiceImpl<Category, Long, CategoryRepository> {


    private final CategoryRepository repository;

    @Autowired
    public CategoryService(CategoryRepository abstractRepository) {
        super(abstractRepository);
        this.repository = abstractRepository;
    }

    public boolean isTableEmpty() {
        return repository.count() == 0;
    }

    public Category findByTopic(String topic) {
        return repository.findByTopic(topic)
                .orElseThrow(() -> new NotFoundException("Topic not found"));
    }
}
