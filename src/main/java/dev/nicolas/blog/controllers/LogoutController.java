package dev.nicolas.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LogoutController {
    @GetMapping("logout")
    public String logout(HttpServletRequest request) {
        try {
            request.logout();
        } catch (ServletException e) {
            System.out.println(e.getMessage());
        }

        return "redirect:/";
    }
}
