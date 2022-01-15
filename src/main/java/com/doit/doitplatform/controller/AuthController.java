package com.doit.doitplatform.controller;

import com.doit.doitplatform.dto.SignUpDTO;
import com.doit.doitplatform.exception.NotFoundException;
import com.doit.doitplatform.model.Category;
import com.doit.doitplatform.model.PageStyle;
import com.doit.doitplatform.model.Role;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

@Controller
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final CategoryService categoryService;
    private final UserService userService;

    @Autowired
    public AuthController(PasswordEncoder passwordEncoder, UserService userService, AuthenticationManager authenticationManager, CategoryService categoryService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.categoryService = categoryService;
    }

    @PostMapping("/sign-up")
    public String signUp(SignUpDTO signUpDTO, HttpServletRequest request) {
        User user = new User();
        user.setUsername(signUpDTO.getUsername());
        user.setEmail(signUpDTO.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
        user.setRoles(new ArrayList<Role>() {{
            add(Role.ROLE_USER);
        }});
        User u = userService.save(user);
        try {
            request.login(signUpDTO.getUsername(), signUpDTO.getPassword());
        } catch (Exception e) {
            throw new NotFoundException("");
        }


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
        style.setBackgroundImage("http://localhost:8083/image/ai");
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

//    @GetMapping("/image/{imageName}")
//    public ResponseEntity<?> getImage(@PathVariable String imageName) {
//
//        Resource resource = getFileResource("/home/afshinpy/iam/image.jpg");
//        return ResponseEntity.ok()
//                .contentType(MediaType.IMAGE_PNG)
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + "image" + "\"")
//                .body(resource);
//    }
//
//    public Resource getFileResource(String fileUri) {
//        try {
//            Resource resource = new UrlResource(fileUri);
//            if (resource.exists())
//                return resource;
//            else throw new NotFoundException(String.format("file with path %s not found", fileUri));
//        } catch (MalformedURLException e) {
//            System.out.println("herre");
//        }
//        return null;
//    }

    @SneakyThrows
    @GetMapping("/image/{imageName}")
    public void getImageAsByteArray(HttpServletResponse response) throws IOException {
        File file = new File("/home/afshinpy/iam/image.jpg");
        InputStream in = new FileInputStream(file);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }

}
