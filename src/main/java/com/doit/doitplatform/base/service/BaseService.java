package com.doit.doitplatform.base.service;

import com.doit.doitplatform.base.model.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public interface BaseService<ENTITY extends BaseEntity<PK>, PK extends Serializable> {
    Page<ENTITY> findAll(Pageable pageable);

    List<ENTITY> findAll();

    Page<ENTITY> findAllNotDeleted(Pageable pageable);

    List<ENTITY> findAllNotDeleted();

    ENTITY save(ENTITY object);

    ENTITY save(ENTITY object, PK createdBy);

    ENTITY update(ENTITY object, PK updatedBy);

    void delete(ENTITY object);

    void deleteById(PK id);

    Long countAll();

    ENTITY getById(PK id);

    void softDelete(ENTITY object);

    void softDeleteById(PK id);
}

