package com.doit.doitplatform.service;

import com.doit.doitplatform.base.service.BaseServiceImpl;
import com.doit.doitplatform.model.User;
import com.doit.doitplatform.model.UserPageStyle;
import com.doit.doitplatform.repository.UserPageStyleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPageStyleService extends BaseServiceImpl<UserPageStyle, Long, UserPageStyleRepository> {

    private final UserPageStyleRepository repository;

    public UserPageStyleService(UserPageStyleRepository abstractRepository) {
        super(abstractRepository);
        this.repository = abstractRepository;
    }

    public Optional<UserPageStyle> findByUser(User user) {
        return repository.findByUser(user);
    }
}
