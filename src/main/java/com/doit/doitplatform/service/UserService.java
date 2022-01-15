package com.doit.doitplatform.service;

import com.doit.doitplatform.base.service.BaseServiceImpl;
import com.doit.doitplatform.dto.SignUpDTO;
import com.doit.doitplatform.exception.NotFoundException;
import com.doit.doitplatform.model.Role;
import com.doit.doitplatform.model.User;
import com.doit.doitplatform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService extends BaseServiceImpl<User, Long, UserRepository> implements UserDetailsService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository abstractRepository, PasswordEncoder passwordEncoder) {
        super(abstractRepository);
        this.repository = abstractRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(String.format("User by username %s not found", username)));
    }

    public User createUser(SignUpDTO signUpDTO) {
        User user = new User();
        user.setUsername(signUpDTO.getUsername());
        user.setEmail(signUpDTO.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
        user.setRoles(new ArrayList<Role>() {{
            add(Role.ROLE_USER);
        }});
        return save(user);
    }
}
