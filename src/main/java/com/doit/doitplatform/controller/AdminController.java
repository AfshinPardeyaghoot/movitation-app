package com.doit.doitplatform.controller;

import com.doit.doitplatform.model.User;
import com.doit.doitplatform.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/adminPanel")
    public String adminPanel(Model model, Principal principal) {
        User user = userService.findByUser(principal.getName());
        model.addAttribute("username", user.getUsername());
        return "admin-panel";
    }


}
