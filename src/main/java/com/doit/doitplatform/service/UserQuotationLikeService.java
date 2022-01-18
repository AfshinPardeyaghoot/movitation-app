package com.doit.doitplatform.service;

import com.doit.doitplatform.base.service.BaseServiceImpl;
import com.doit.doitplatform.model.Quotation;
import com.doit.doitplatform.model.User;
import com.doit.doitplatform.model.UserQuotationLike;
import com.doit.doitplatform.repository.UserQuotationLikeRepository;
import org.springframework.stereotype.Service;

@Service
public class UserQuotationLikeService extends BaseServiceImpl<UserQuotationLike, Long, UserQuotationLikeRepository> {

    private final UserQuotationLikeRepository repository;

    public UserQuotationLikeService(UserQuotationLikeRepository abstractRepository) {
        super(abstractRepository);
        this.repository = abstractRepository;
    }

    public UserQuotationLike likeQuotation(Quotation quotation, User user) {

        return null;
    }
}
