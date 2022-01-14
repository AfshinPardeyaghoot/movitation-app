package com.doit.doitplatform.service;

import com.doit.doitplatform.base.service.BaseServiceImpl;
import com.doit.doitplatform.exception.NotFoundException;
import com.doit.doitplatform.model.User;
import com.doit.doitplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseServiceImpl<User, Long, UserRepository> implements UserDetailsService {

    private UserRepository repository;

    @Autowired
    public UserService(UserRepository abstractRepository) {
        super(abstractRepository);
        this.repository = abstractRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(String.format("User by username %s not found", username)));
    }
}
