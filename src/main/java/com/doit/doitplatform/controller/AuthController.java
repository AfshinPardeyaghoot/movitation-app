package com.doit.doitplatform.controller;

import com.doit.doitplatform.dto.SignUpDTO;
import com.doit.doitplatform.model.User;
import com.doit.doitplatform.service.CategoryService;
import com.doit.doitplatform.service.UserService;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @SneakyThrows
    @PostMapping("/sign-up")
    public String signUp(SignUpDTO signUpDTO, HttpServletRequest request) {

        User u = userService.createUser(signUpDTO);
        request.login(signUpDTO.getUsername(), signUpDTO.getPassword());
        return "redirect:/index";
    }


    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("signUpDTO", new SignUpDTO());
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }


}
