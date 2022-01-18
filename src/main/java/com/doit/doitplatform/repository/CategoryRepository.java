package com.doit.doitplatform.repository;

import com.doit.doitplatform.base.repository.AbstractRepository;
import com.doit.doitplatform.model.Category;
import com.doit.doitplatform.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends AbstractRepository<Category, Long> {
    Optional<Category> findByTopic(String topic);

    Optional<Category> findByTopicAndCreator(String topic, User user);
}
