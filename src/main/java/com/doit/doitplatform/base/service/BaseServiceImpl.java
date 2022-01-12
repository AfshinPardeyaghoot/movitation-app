package com.doit.doitplatform.base.service;


import com.doit.doitplatform.base.model.BaseEntity;
import com.doit.doitplatform.base.repository.AbstractRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseServiceImpl<ENTITY extends BaseEntity<PK>,
        PK extends Serializable, REPOSITORY extends AbstractRepository<ENTITY, PK>> implements BaseService<ENTITY, PK> {
    private final REPOSITORY abstractRepository;
    protected Class<ENTITY> entityClass;

    public BaseServiceImpl(REPOSITORY abstractRepository) {
        this.abstractRepository = abstractRepository;
        if (getClass().getGenericSuperclass() == (ParameterizedType) getClass().getGenericSuperclass()) {
            ParameterizedType genericSuperClass = (ParameterizedType) getClass().getGenericSuperclass();
            if (genericSuperClass != null && genericSuperClass.getActualTypeArguments() != null
                    && genericSuperClass.getActualTypeArguments().length > 0) {
                if (genericSuperClass.getActualTypeArguments()[0] instanceof Class) {
                    entityClass = (Class<ENTITY>) genericSuperClass.getActualTypeArguments()[0];
                }
            }
        }
    }

    @Override
    public Page<ENTITY> findAll(Pageable pageable) {
        return abstractRepository.findAll(pageable);
    }

    @Override
    public List<ENTITY> findAll() {
        return abstractRepository.findAll();
    }

    @Override
    public Page<ENTITY> findAllNotDeleted(Pageable pageable) {
        return abstractRepository.findAllByIsDeleted(false, pageable);
    }

    @Override
    public List<ENTITY> findAllNotDeleted() {
        return abstractRepository.findAllByIsDeleted(false);
    }

    @Override
    public ENTITY save(ENTITY object) {
        return abstractRepository.save(object);
    }

    @Override
    public ENTITY save(ENTITY object, PK createdBy) {
        object.setCreatedBy(createdBy);
        return save(object);
    }

    @Override
    public ENTITY update(ENTITY object, PK updatedBy) {
        object.setUpdateBy(updatedBy);
        return save(object);
    }


    @Override
    public Long countAll() {
        return abstractRepository.count();
    }

    @Override
    public ENTITY getById(PK id) {
        return abstractRepository.findById(id).orElseThrow(() ->
                null// new NotFoundException(String.format("object %d from %s not found", id, entityClass))
        );
    }

    @Override
    public void delete(ENTITY object) {
        abstractRepository.delete(object);
    }

    @Override
    public void deleteById(PK id) {
        ENTITY object = getById(id);
        abstractRepository.delete(object);
    }

    @Override
    public void softDelete(ENTITY object) {
        object.setIsDeleted(true);
        save(object);
    }

    @Override
    public void softDeleteById(PK id) {
        ENTITY object = getById(id);
        object.setIsDeleted(true);
        save(object);
    }

}
