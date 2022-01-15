package com.doit.doitplatform.service;

import com.doit.doitplatform.base.service.BaseServiceImpl;
import com.doit.doitplatform.model.UserPageStyle;
import com.doit.doitplatform.repository.UserPageStyleRepository;
import org.springframework.stereotype.Service;

@Service
public class UserPageStyleService extends BaseServiceImpl<UserPageStyle, Long, UserPageStyleRepository> {

    private final UserPageStyleRepository repository;

    public UserPageStyleService(UserPageStyleRepository abstractRepository) {
        super(abstractRepository);
        this.repository = abstractRepository;
    }
}
