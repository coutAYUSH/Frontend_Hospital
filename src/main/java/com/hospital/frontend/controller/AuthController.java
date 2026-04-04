package com.hospital.frontend.controller;

import com.hospital.frontend.model.LoginRequest;
import com.hospital.frontend.model.LoginResponse;
import com.hospital.frontend.service.ApiService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final ApiService apiService;

    public AuthController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping({"/", "/login"})
    public String loginPage(HttpSession session) {
        if (session.getAttribute("token") != null) {
            String role = (String) session.getAttribute("role");
            return redirectByRole(role);
        }
        return "auth/login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String email,
                          @RequestParam String password,
                          HttpSession session,
                          Model model) {
        LoginRequest req = new LoginRequest();
        req.setEmail(email);
        req.setPassword(password);

        LoginResponse resp = apiService.login(req);

        if (resp == null || resp.getToken() == null) {
            model.addAttribute("error", "Invalid credentials. Please try again.");
            return "auth/login";
        }

        session.setAttribute("token", resp.getToken());
        session.setAttribute("role", resp.getRole());
        session.setAttribute("userEmail", email);

        return "redirect:" + redirectByRole(resp.getRole()).replace("redirect:", "");
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    private String redirectByRole(String role) {
        if (role == null) return "redirect:/login";
        return switch (role.toUpperCase()) {
            case "NURSE" -> "redirect:/nurse/appointments";
            case "PHYSICIAN" -> "redirect:/physician/patients";
            default -> "redirect:/staff/dashboard";
        };
    }
}
