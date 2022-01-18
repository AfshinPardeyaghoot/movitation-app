package com.doit.doitplatform.repository;

import com.doit.doitplatform.base.repository.AbstractRepository;
import com.doit.doitplatform.model.Quotation;
import com.doit.doitplatform.model.User;
import com.doit.doitplatform.model.UserQuotationLike;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserQuotationLikeRepository extends AbstractRepository<com.doit.doitplatform.model.UserQuotationLike, Long> {
    Optional<UserQuotationLike> findByUserAndQuotation(User user, Quotation quotation);

    Optional<UserQuotationLike> findByUserAndQuotationAndIsDeleted(User user, Quotation quotation, Boolean isDeleted);

    @Query("select quote.id from UserQuotationLike userLike join Quotation quote on userLike.quotation = quote where userLike.user =:user and userLike.isDeleted = false ")
    List<Long> getQuotationIdsUserLiked(User user);
}
