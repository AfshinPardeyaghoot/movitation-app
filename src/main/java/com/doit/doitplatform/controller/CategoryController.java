package com.doit.doitplatform.controller;

import com.doit.doitplatform.exception.AccessDeniedException;
import com.doit.doitplatform.model.Category;
import com.doit.doitplatform.model.Quotation;
import com.doit.doitplatform.model.QuotationCategory;
import com.doit.doitplatform.model.User;
import com.doit.doitplatform.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class CategoryController {

    private final UserService userService;
    private final CategoryService categoryService;
    private final QuotationService quotationService;
    private final QuotationCategoryService quotationCategoryService;
    private final UserQuotationLikeService userQuotationLikeService;

    @Autowired
    public CategoryController(UserService userService, CategoryService categoryService, QuotationService quotationService, QuotationCategoryService quotationCategoryService, UserQuotationLikeService userQuotationLikeService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.quotationService = quotationService;
        this.quotationCategoryService = quotationCategoryService;
        this.userQuotationLikeService = userQuotationLikeService;
    }

    @GetMapping("/categories")
    public String categoryPage(Model model, Principal principal) {
        User user = userService.findByUser(principal.getName());
        List<Category> categories = categoryService.getGeneralAndUserCategories(user);
        model.addAttribute("categories", categories);
        return "categories";
    }

    @PostMapping("/category")
    public String creatCategory(String categoryName, Principal principal) {
        User user = userService.findByUser(principal.getName());
        Category category = categoryService.create(categoryName, user);
        return "redirect:/categories";
    }

    @GetMapping("/category/edit")
    public String editCategory(Long categoryId, Model model) {
        Category category = categoryService.getById(categoryId);
        model.addAttribute("category", category);
        model.addAttribute("quotations", quotationService.findAllByCategory(category));
        return "edit-category";
    }

    @PostMapping("/category/addQuote")
    public String addQuoteToCategory(Long categoryId, String quotation, Principal principal, RedirectAttributes model) {
        User user = userService.findByUser(principal.getName());
        model.addAttribute("categoryId", categoryId);
        if (quotation == null || quotation.length() == 0)
            return "redirect:/category/edit";
        Category category = categoryService.getById(categoryId);
        Quotation quote = quotationService.create(quotation);
        quotationCategoryService.create(quote, category);
        return "redirect:/category/edit";
    }


    @GetMapping("/category/deleteQoute")
    public String deleteQuoteFormCategory(Integer quoteId, Long categoryId, Principal principal, RedirectAttributes model) {
        model.addAttribute("categoryId", categoryId);
        User user = userService.findByUser(principal.getName());
        Category category = categoryService.getById(categoryId);
        if (quoteId == 0) {
            return "redirect:/category/edit";
        }
        if (category.getCreator() != user) {
            throw new AccessDeniedException("دسترسی غیر مجاز");
        }
        Quotation quotation = quotationService.getById(Long.valueOf(quoteId));
        userQuotationLikeService.disLikeQuotation(quotation, user);
        QuotationCategory quotationCategory = quotationCategoryService.findByQuotationAndCategory(quotation, category);
        quotationCategoryService.softDelete(quotationCategory);
        return "redirect:/category/edit";
    }
}
