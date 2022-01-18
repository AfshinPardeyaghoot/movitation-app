package com.doit.doitplatform.controller;

import com.doit.doitplatform.dto.QuotationGetDTO;
import com.doit.doitplatform.model.*;
import com.doit.doitplatform.service.*;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class StyleController {

    private final CategoryService categoryService;
    private final UserPageStyleService userPageStyleService;
    private final UserCategoryService userCategoryService;
    private final PageStyleService pageStyleService;
    private final UserService userService;
    private final QuotationService quotationService;
    private final QuotationCategoryService quotationCategoryService;
    private final UserQuotationLikeService userQuotationLikeService;
    private final String BASE_URL;

    @Autowired
    public StyleController(@Value("${application.base.url}") String BASE_URL, CategoryService categoryService, UserPageStyleService userPageStyleService, UserService userService, PageStyleService pageStyleService, UserCategoryService userCategoryService, QuotationService quotationService, QuotationCategoryService quotationCategoryService, UserQuotationLikeService userQuotationLikeService) {
        this.categoryService = categoryService;
        this.BASE_URL = BASE_URL;
        this.userPageStyleService = userPageStyleService;
        this.pageStyleService = pageStyleService;
        this.userService = userService;
        this.quotationCategoryService = quotationCategoryService;
        this.quotationService = quotationService;
        this.userQuotationLikeService = userQuotationLikeService;
        this.userCategoryService = userCategoryService;
    }

    @GetMapping("/styles")
    public String styleController(Model model) {
        List<PageStyle> pageStyleList = pageStyleService.findAllNotDeleted();
        model.addAttribute("pageStyleList", pageStyleList);
        return "styles";
    }

    @RequestMapping("/index")
    public String indexPage(Model model) {
        return "index";
    }

    @GetMapping("/categories")
    public String categoryPage(Model model) {
        System.out.println("in categories");
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "categories";
    }

    @ModelAttribute("pageStyle")
    public PageStyle style(Principal principal) {
        User user = userService.findByUser(principal.getName());
        if (userPageStyleService.findByUser(user).isPresent()) {
            return userPageStyleService.findByUser(user).get().getPageStyle();
        }
        PageStyle style = new PageStyle();
        style.setBackgroundImage(BASE_URL + "/image/image5.jpg");
        style.setFontColor("#EC9F3B");
        style.setFontFile("font3");
        return style;
    }

    @ModelAttribute("quotations")
    public List<QuotationGetDTO> quotation(Principal principal) {
        User user = userService.findByUser(principal.getName());
        Category category = null;
        if (userCategoryService.findByUser(user).isPresent()) {
            category = userCategoryService.findByUser(user).get().getCategory();
        } else {
            category = categoryService.findByTopic("General");
        }

        List<QuotationGetDTO> result = quotationService
                .findAllByCategory(category)
                .stream()
                .map(quotation -> quotation.quotationGetDTO(userQuotationLikeService.quotationIdsUserLiked(user)))
                .collect(Collectors.toList());
        return result;
    }

    @SneakyThrows
    @GetMapping("/image/{imageName}")
    public void getImageAsByteArray(HttpServletResponse response, @PathVariable String imageName) throws IOException {
        File file = new File(String.format("/home/afshinpy/iam/%s", imageName));
        InputStream in = new FileInputStream(file);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
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

    @ResponseBody
    @PostMapping("/like")
    public ResponseEntity<?> likeQuotation(@RequestBody String quoteId, Principal principal) {
        Long quotationId = Long.valueOf(quoteId);
        User user = userService.findByUser(principal.getName());
        Quotation quotation = quotationService.getById(quotationId);
        Category category = categoryService.getUserLikeCategory(user);
        if (userQuotationLikeService.isUserLikedQuotation(user, quotation)) {
            quotationCategoryService.softDelete(quotationCategoryService.findByQuotationAndCategory(quotation, category));
            userQuotationLikeService.disLikeQuotation(quotation, user);

        } else {
            quotationCategoryService.create(quotation, category);
            userQuotationLikeService.likeQuotation(quotation, user);
        }
        return ResponseEntity.ok(true);
    }

}
