package com.doit.doitplatform.controller;

import com.doit.doitplatform.dto.SignUpDTO;
import com.doit.doitplatform.model.Category;
import com.doit.doitplatform.model.PageStyle;
import com.doit.doitplatform.model.User;
import com.doit.doitplatform.service.CategoryService;
import com.doit.doitplatform.service.UserService;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final CategoryService categoryService;
    private final UserService userService;
    private final String BASE_URL;

    @Autowired
    public AuthController(@Value("${application.base.url}") String BASE_URL, PasswordEncoder passwordEncoder, UserService userService, AuthenticationManager authenticationManager, CategoryService categoryService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.categoryService = categoryService;
        this.BASE_URL = BASE_URL;
    }

    @SneakyThrows
    @PostMapping("/sign-up")
    public String signUp(SignUpDTO signUpDTO, HttpServletRequest request) {

        User u = userService.createUser(signUpDTO);
        request.login(signUpDTO.getUsername(), signUpDTO.getPassword());
        return "redirect:/index";
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

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("signUpDTO", new SignUpDTO());
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "login";
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
