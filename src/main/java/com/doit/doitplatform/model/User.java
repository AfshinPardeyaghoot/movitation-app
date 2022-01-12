package com.doit.doitplatform.model;

import com.doit.doitplatform.base.model.BaseEntity;

import javax.persistence.Table;

@Table(name = "users")
public class User extends BaseEntity<Long> {

    private String username;
    private String email;
    private String password;
}
