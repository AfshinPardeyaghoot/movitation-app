package com.doit.doitplatform.controller;

import com.doit.doitplatform.model.Category;
import com.doit.doitplatform.model.PageStyle;
import com.doit.doitplatform.model.User;
import com.doit.doitplatform.model.UserPageStyle;
import com.doit.doitplatform.service.CategoryService;
import com.doit.doitplatform.service.PageStyleService;
import com.doit.doitplatform.service.UserPageStyleService;
import com.doit.doitplatform.service.UserService;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
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

@Controller
public class StyleController {

    private final CategoryService categoryService;
    private final UserPageStyleService userPageStyleService;
    private final PageStyleService pageStyleService;
    private final UserService userService;
    private final String BASE_URL;

    @Autowired
    public StyleController(@Value("${application.base.url}") String BASE_URL, CategoryService categoryService, UserPageStyleService userPageStyleService, UserService userService, PageStyleService pageStyleService) {
        this.categoryService = categoryService;
        this.BASE_URL = BASE_URL;
        this.userPageStyleService = userPageStyleService;
        this.pageStyleService = pageStyleService;
        this.userService = userService;
    }

    @RequestMapping("/index")
    public String afterLogin(Model model) {
        Category category = categoryService.getById(3L);
        model.addAttribute("quotations", category.getQuotations());
        return "index";
    }

    @GetMapping("/pageStyleGroup")
    public String pageStyleGroup(Model model) {
        List<PageStyle> pageStyleList = pageStyleService.findAllNotDeleted();
        model.addAttribute("pageStyleList", pageStyleList);
        return "pageStyleGroup";
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
        return style;
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

}
