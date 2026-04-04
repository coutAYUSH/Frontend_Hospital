package com.hospital.frontend.controller;

import com.hospital.frontend.model.StaffRegisterRequest;
import com.hospital.frontend.service.ApiService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/staff")
public class StaffController {

    private final ApiService apiService;

    public StaffController(ApiService apiService) {
        this.apiService = apiService;
    }

    private String getToken(HttpSession session) {
        return (String) session.getAttribute("token");
    }

    private boolean isUnauthorized(HttpSession session) {
        return session.getAttribute("token") == null;
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        if (isUnauthorized(session)) return "redirect:/login";
        model.addAttribute("role", session.getAttribute("role"));
        model.addAttribute("userEmail", session.getAttribute("userEmail"));
        model.addAttribute("activeTab", "dashboard");
        return "staff/dashboard";
    }

    @GetMapping("/allpatients")
    public String allPatients(HttpSession session, Model model) {
        if (isUnauthorized(session)) return "redirect:/login";
        model.addAttribute("patients", apiService.getAllPatients(getToken(session)));
        model.addAttribute("role", session.getAttribute("role"));
        model.addAttribute("userEmail", session.getAttribute("userEmail"));
        model.addAttribute("activeTab", "allpatients");
        return "staff/allpatients";
    }
}