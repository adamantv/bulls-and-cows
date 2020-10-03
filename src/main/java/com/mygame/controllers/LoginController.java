package com.mygame.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Контроллер для адреса "/login"
 */
@Controller
public class LoginController {
    @GetMapping("/login")
    public String get() {
        return "/login";
    }
}