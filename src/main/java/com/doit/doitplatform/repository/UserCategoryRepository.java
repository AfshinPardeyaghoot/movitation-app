package com.doit.doitplatform.repository;

import com.doit.doitplatform.base.repository.AbstractRepository;
import com.doit.doitplatform.model.User;
import com.doit.doitplatform.model.UserCategory;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCategoryRepository extends AbstractRepository<UserCategory, Long> {
    Optional<UserCategory> findByUser(User user);
}
