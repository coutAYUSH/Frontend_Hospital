package com.hospital.frontend.controller;

import com.hospital.frontend.service.ApiService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/nurse")
public class NurseController {

    private final ApiService apiService;

    public NurseController(ApiService apiService) {
        this.apiService = apiService;
    }

    private String getToken(HttpSession session) {
        return (String) session.getAttribute("token");
    }

    private boolean isUnauthorized(HttpSession session) {
        String role = (String) session.getAttribute("role");
        return session.getAttribute("token") == null ||
                (role != null && !role.equalsIgnoreCase("NURSE"));
    }

    @GetMapping("/appointments")
    public String appointments(HttpSession session, Model model) {
        if (isUnauthorized(session)) return "redirect:/login";
        model.addAttribute("appointments", apiService.getNurseAppointments(getToken(session)));
        model.addAttribute("activeTab", "appointments");
        model.addAttribute("role", session.getAttribute("role"));
        model.addAttribute("userEmail", session.getAttribute("userEmail"));
        return "nurse/appointments";
    }

    @GetMapping("/oncalls")
    public String oncalls(HttpSession session, Model model) {
        if (isUnauthorized(session)) return "redirect:/login";
        model.addAttribute("oncalls", apiService.getNurseOncalls(getToken(session)));
        model.addAttribute("activeTab", "oncalls");
        model.addAttribute("role", session.getAttribute("role"));
        model.addAttribute("userEmail", session.getAttribute("userEmail"));
        return "nurse/oncalls";
    }

    @GetMapping("/allnurse")
    public String allNurses(HttpSession session, Model model) {
        if (isUnauthorized(session)) return "redirect:/login";
        model.addAttribute("nurses", apiService.getAllNurses(getToken(session)));
        model.addAttribute("activeTab", "allnurse");
        model.addAttribute("role", session.getAttribute("role"));
        model.addAttribute("userEmail", session.getAttribute("userEmail"));
        return "nurse/allnurse";
    }
}