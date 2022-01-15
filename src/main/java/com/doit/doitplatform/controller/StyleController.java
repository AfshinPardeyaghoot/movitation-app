package com.doit.doitplatform.controller;

import com.doit.doitplatform.model.Category;
import com.doit.doitplatform.model.PageStyle;
import com.doit.doitplatform.service.CategoryService;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class StyleController {

    private final CategoryService categoryService;
    private final String BASE_URL;

    @Autowired
    public StyleController(@Value("${application.base.url}") String BASE_URL, CategoryService categoryService) {
        this.categoryService = categoryService;
        this.BASE_URL = BASE_URL;
    }

    @RequestMapping("/index")
    public String afterLogin(Model model) {
        Category category = categoryService.getById(1L);
        model.addAttribute("quotations", category.getQuotations());
        return "index";
    }

    @ModelAttribute("pageStyle")
    public PageStyle style() {
        PageStyle style = new PageStyle();
        style.setBackgroundImage(BASE_URL + "/image/ai");
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
}
