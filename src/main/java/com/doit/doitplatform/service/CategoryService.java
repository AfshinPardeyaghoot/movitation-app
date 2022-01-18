package com.doit.doitplatform.service;

import com.doit.doitplatform.base.service.BaseServiceImpl;
import com.doit.doitplatform.exception.NotFoundException;
import com.doit.doitplatform.model.Category;
import com.doit.doitplatform.model.User;
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

    public Category getUserLikeCategory(User user) {
        Category result = null;
        if (repository.findByTopicAndCreator("Like", user).isPresent())
            result = repository.findByTopicAndCreator("Like", user).get();
        else {
            result = new Category();
            result.setTopic("Like");
            result.setCreator(user);
            result.setIsDeleted(false);
            result.setIsGeneral(false);
            save(result);
        }

        return result;
    }
}
