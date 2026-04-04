package com.hospital.frontend.controller;



import com.hospital.frontend.service.ApiService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/physician")
public class PhysicianController {

    private final ApiService apiService;

    public PhysicianController(ApiService apiService) {
        this.apiService = apiService;
    }

    private String getToken(HttpSession session) {
        return (String) session.getAttribute("token");
    }

    private boolean isUnauthorized(HttpSession session) {
        String role = (String) session.getAttribute("role");
        return session.getAttribute("token") == null ||
                (role != null && !role.equalsIgnoreCase("PHYSICIAN"));
    }



    @GetMapping("/patients")
    public String patients(HttpSession session, Model model) {
        if (isUnauthorized(session)) return "redirect:/login";
        model.addAttribute("patients", apiService.getPhysicianPatients(getToken(session)));
        model.addAttribute("activeTab", "patients");
        model.addAttribute("role", session.getAttribute("role"));
        model.addAttribute("userEmail", session.getAttribute("userEmail"));
        return "physician/patients";
    }

    @GetMapping("/appointments")
    public String appointments(HttpSession session, Model model) {
        if (isUnauthorized(session)) return "redirect:/login";
        model.addAttribute("appointments", apiService.getPhysicianAppointments(getToken(session)));
        model.addAttribute("activeTab", "appointments");
        model.addAttribute("role", session.getAttribute("role"));
        model.addAttribute("userEmail", session.getAttribute("userEmail"));
        return "physician/appointments";
    }
    @GetMapping("/prescribes")
    public String prescribes(HttpSession session, Model model) {
        if (isUnauthorized(session)) return "redirect:/login";
        model.addAttribute("prescribes", apiService.getPhysicianPrescribes(getToken(session)));
        model.addAttribute("activeTab", "prescribes");
        model.addAttribute("role", session.getAttribute("role"));
        model.addAttribute("userEmail", session.getAttribute("userEmail"));
        return "physician/prescribes";
    }
}