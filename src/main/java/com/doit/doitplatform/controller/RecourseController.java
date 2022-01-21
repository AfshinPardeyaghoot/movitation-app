package com.doit.doitplatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/static")
public class RecourseController {

    @GetMapping("/css/login.css")
    public String getLoginStyle() {
        return "../static/css/login.css";
    }

    @GetMapping("/js/login.js")
    public String getLoginJs() {
        return "../static/js/login.js";
    }

    @GetMapping("/css/index.css")
    public String getIndexCss() {
        return "../static/css/index.css";
    }

    @GetMapping("/css/categories.css")
    public String categoriesCss() {
        return "../static/css/categories.css";
    }

    @GetMapping("/css/styles.css")
    public String stylesCss() {
        return "../static/css/styles.css";
    }

    @GetMapping("/css/edit-category.css")
    public String editCategoryCss() {
        return "../static/css/edit-category.css";
    }

    @GetMapping("/css/admin-panel.css")
    public String adminPanelCss() {
        return "../static/css/admin-panel.css";
    }


}
