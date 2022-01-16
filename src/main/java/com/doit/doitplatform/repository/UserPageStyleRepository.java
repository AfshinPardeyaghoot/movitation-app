package com.doit.doitplatform.repository;

import com.doit.doitplatform.base.repository.AbstractRepository;
import com.doit.doitplatform.model.User;
import com.doit.doitplatform.model.UserPageStyle;

import java.util.Optional;

public interface UserPageStyleRepository extends AbstractRepository<UserPageStyle, Long> {
    Optional<UserPageStyle> findByUser(User user);
}
