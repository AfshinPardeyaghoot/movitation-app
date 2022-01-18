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
        UserQuotationLike userQuotationLike;
        if (repository.findByUserAndQuotation(user, quotation).isPresent()) {
            userQuotationLike = repository.findByUserAndQuotation(user, quotation).get();
        } else {
            userQuotationLike = new UserQuotationLike();
            userQuotationLike.setQuotation(quotation);
            userQuotationLike.setUser(user);
        }
        userQuotationLike.setIsDeleted(false);
        return repository.save(userQuotationLike);
    }

    public boolean isUserLikedQuotation(User user, Quotation quotation) {
        return repository.findByUserAndQuotationAndIsDeleted(user, quotation, false).isPresent();
    }

    public UserQuotationLike disLikeQuotation(Quotation quotation, User user) {
        UserQuotationLike userQuotationLike = repository.findByUserAndQuotationAndIsDeleted(user, quotation, false).get();
        userQuotationLike.setIsDeleted(true);
        return repository.save(userQuotationLike);
    }
}
