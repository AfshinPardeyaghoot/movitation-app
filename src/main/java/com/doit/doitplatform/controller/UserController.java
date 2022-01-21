package com.doit.doitplatform.controller;

import com.doit.doitplatform.model.*;
import com.doit.doitplatform.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class UserController {
    private final UserService userService;
    private final UserPageStyleService userPageStyleService;
    private final PageStyleService pageStyleService;
    private final CategoryService categoryService;
    private final UserCategoryService userCategoryService;


    @Autowired
    public UserController(UserService userService, UserPageStyleService userPageStyleService, PageStyleService pageStyleService, CategoryService categoryService, UserCategoryService userCategoryService) {
        this.userService = userService;
        this.userPageStyleService = userPageStyleService;
        this.pageStyleService = pageStyleService;
        this.categoryService = categoryService;
        this.userCategoryService = userCategoryService;
    }

    @PostMapping("/user/style")
    public String setUserStyle(Long styleId, Principal principal) {
        UserPageStyle userPageStyle = null;
        User user = userService.findByUser(principal.getName());
        PageStyle pageStyle = pageStyleService.getById(styleId);
        if (userPageStyleService.findByUser(user).isPresent()) {
            userPageStyle = userPageStyleService.findByUser(user).get();
            userPageStyle.setPageStyle(pageStyle);
        } else {
            userPageStyle = new UserPageStyle();
            userPageStyle.setPageStyle(pageStyle);
            userPageStyle.setUser(user);
            userPageStyle.setIsDeleted(false);
        }
        userPageStyleService.save(userPageStyle);
        return "redirect:/index";
    }

    @PostMapping("/user/category")
    public String setUserCategory(Long categoryId, Principal principal) {
        User user = userService.findByUser(principal.getName());
        Category category = categoryService.getById(categoryId);
        UserCategory userCategory = null;
        if (userCategoryService.findByUser(user).isPresent()) {
            userCategory = userCategoryService.findByUser(user).get();
            userCategory.setCategory(category);
        } else {
            userCategory = new UserCategory();
            userCategory.setUser(user);
            userCategory.setCategory(category);
            userCategory.setIsDeleted(false);
        }

        userCategoryService.save(userCategory);
        return "redirect:/index";
    }

}
