package com.doit.doitplatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/static")
public class RecourseController {

    @GetMapping("/css/login.css")
    public String getLoginStyle(){
        return "../static/css/login.css";
    }

    @GetMapping("/js/login.js")
    public String getLoginJs(){
        return "../static/js/login.js";
    }

    @GetMapping("/js/index.js")
    public String getIndexJs(){
        return "../static/js/index.js";
    }

    @GetMapping("/css/index.css")
    public String getIndexCss(){
        return "../static/css/index.css";
    }
}
