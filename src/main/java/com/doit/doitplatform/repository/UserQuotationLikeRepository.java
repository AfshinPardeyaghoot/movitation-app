package com.doit.doitplatform.repository;

import com.doit.doitplatform.base.repository.AbstractRepository;
import com.doit.doitplatform.model.Quotation;
import com.doit.doitplatform.model.User;
import com.doit.doitplatform.model.UserQuotationLike;

import java.util.Optional;

public interface UserQuotationLikeRepository extends AbstractRepository<com.doit.doitplatform.model.UserQuotationLike, Long> {
    Optional<UserQuotationLike> findByUserAndQuotation(User user, Quotation quotation);
}
