package com.mygame.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import com.mygame.domain.User;
import com.mygame.repositories.UserRepository;

/**
 * Контроллер для адреса "/registration"
 */
@Controller
public class RegistrationController {
    private PasswordEncoder passwordEncoder;

    private UserRepository userRepository;

    public RegistrationController(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }


    @GetMapping(value = "/registration")
    public String get(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    /**
     * Регистрация нового пользователя. Если пользователь с таким именем уже существует, выполняется
     * перенаправление обратно на страницу регистрации с ошибкой.
     */
    @PostMapping(value = "/registration")
    public String registerUserAccount(@ModelAttribute("user") @Valid User user) {
        if (!createUserAccount(user)) return "redirect:/registration?usernameTaken";
        else return "redirect:/login?registered";
    }

    /**
     * Сохранение нового пользователя в репозиторий (если данное имя пользователя не занято).
     */
    private boolean createUserAccount(User user) {
        if (userRepository.findByUsernameIgnoreCase(user.getUsername()) == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        } else return false;
    }
}
