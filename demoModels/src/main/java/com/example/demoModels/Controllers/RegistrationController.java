package com.example.demoModels.Controllers;


import com.example.demoModels.Models.Role;
import com.example.demoModels.Models.User;
import com.example.demoModels.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    private String RegView()
    {
        return "Avtoriz/regis";
    }

    @PostMapping("/registration")
    private String Reg(User user, Model model)
    {
        User user_from_db = userRepository.findUserByUsername(user.getUsername());
        if(user_from_db != null)
        {
            model.addAttribute("message","Пользователь с таким логином уже существует");
            return "Avtoriz/regis";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.ADMIN));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return "redirect:/login";
    }

    @GetMapping("/registration/admin")
    private String RegAdminView()
    {
        return "Avtoriz/regisAdmin";
    }

    @PostMapping("/registration/admin")
    private String RegAdmin(User user, Model model)
    {
        User user_from_db = userRepository.findUserByUsername(user.getUsername());
        if(user_from_db != null)
        {
            model.addAttribute("message","Пользователь с таким логином уже существует");
            return "Avtoriz/regisAdmin";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.ADMIN));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return "redirect:/admin/a";
    }
}
