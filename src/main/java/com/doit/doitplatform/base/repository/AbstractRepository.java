package com.doit.doitplatform.base.repository;


import com.doit.doitplatform.base.model.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface AbstractRepository<ENTITY extends BaseEntity<PK>, PK extends Serializable>
        extends JpaRepository<ENTITY, PK> {

    Page<ENTITY> findAllByIsDeleted(Boolean isDeleted, Pageable pageable);

    List<ENTITY> findAllByIsDeleted(Boolean isDeleted);


}