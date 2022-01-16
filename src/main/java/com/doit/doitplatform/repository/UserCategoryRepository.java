package com.doit.doitplatform.repository;

import com.doit.doitplatform.base.repository.AbstractRepository;
import com.doit.doitplatform.model.UserCategory;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCategoryRepository extends AbstractRepository<UserCategory, Long> {
}
