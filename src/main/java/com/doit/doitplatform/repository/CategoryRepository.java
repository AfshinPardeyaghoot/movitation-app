package com.doit.doitplatform.repository;

import com.doit.doitplatform.base.repository.AbstractRepository;
import com.doit.doitplatform.model.Category;
import com.doit.doitplatform.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends AbstractRepository<Category, Long> {
    Optional<Category> findByTopic(String topic);

    Optional<Category> findByTopicAndCreator(String topic, User user);


    @Query("select c from Category c where ((c.isGeneral = true ) or ( c.creator =:user)) and c.isDeleted = false ")
    List<Category> getGeneralAndUserCategories(User user);
}
