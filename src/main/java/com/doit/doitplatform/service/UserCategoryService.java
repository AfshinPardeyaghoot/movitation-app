package com.doit.doitplatform.service;

import com.doit.doitplatform.base.service.BaseServiceImpl;
import com.doit.doitplatform.model.User;
import com.doit.doitplatform.model.UserCategory;
import com.doit.doitplatform.repository.UserCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCategoryService extends BaseServiceImpl<UserCategory, Long, UserCategoryRepository> {

    private final UserCategoryRepository repository;

    @Autowired
    public UserCategoryService(UserCategoryRepository abstractRepository) {
        super(abstractRepository);
        this.repository = abstractRepository;
    }

    public Optional<UserCategory> findByUser(User user) {
        return repository.findByUser(user);
    }
}
