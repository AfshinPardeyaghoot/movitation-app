package com.doit.doitplatform.controller;

import com.doit.doitplatform.dto.SignUpDTO;
import com.doit.doitplatform.exception.NotFoundException;
import com.doit.doitplatform.model.Role;
import com.doit.doitplatform.model.User;
import com.doit.doitplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Autowired
    public AuthController(PasswordEncoder passwordEncoder, UserService userService, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
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
        System.out.println("user authenticated");
        List<String> strings = new ArrayList<>();
        strings.add("Hello");
        strings.add("Welcome");
        strings.add("To");
        strings.add("My");
        strings.add("Dear");
        strings.add("Friends");
        model.addAttribute("strings", strings);
        return "index";
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
