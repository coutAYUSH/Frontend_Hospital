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

    @GetMapping("/allphysicians")
    public String allPhysicians(HttpSession session, Model model) {
        if (isUnauthorized(session)) return "redirect:/login";
        model.addAttribute("physicians", apiService.getAllPhysicians(getToken(session)));
        model.addAttribute("role", session.getAttribute("role"));
        model.addAttribute("userEmail", session.getAttribute("userEmail"));
        model.addAttribute("activeTab", "allphysicians");
        return "staff/allphysicians";
    }

    @GetMapping("/allnurses")
    public String allNurses(HttpSession session, Model model) {
        if (isUnauthorized(session)) return "redirect:/login";
        model.addAttribute("nurses", apiService.getAllNursesStaff(getToken(session)));
        model.addAttribute("role", session.getAttribute("role"));
        model.addAttribute("userEmail", session.getAttribute("userEmail"));
        model.addAttribute("activeTab", "allnurses");
        return "staff/allnurses";
    }

    @GetMapping("/patientById")
    public String patientById(@RequestParam(required = false) Integer id,
                              HttpSession session, Model model) {
        if (isUnauthorized(session)) return "redirect:/login";
        if (id != null) {
            model.addAttribute("patient", apiService.getPatientById(getToken(session), id));
            model.addAttribute("searchId", id);
        }
        model.addAttribute("role", session.getAttribute("role"));
        model.addAttribute("userEmail", session.getAttribute("userEmail"));
        model.addAttribute("activeTab", "patientById");
        return "staff/patientById";
    }

    @GetMapping("/appointments")
    public String appointments(@RequestParam(required = false) Integer id,
                               HttpSession session, Model model) {
        if (isUnauthorized(session)) return "redirect:/login";
        if (id != null) {
            model.addAttribute("appointments", apiService.getAppointmentsById(getToken(session), id));
            model.addAttribute("searchId", id);
        }
        model.addAttribute("role", session.getAttribute("role"));
        model.addAttribute("userEmail", session.getAttribute("userEmail"));
        model.addAttribute("activeTab", "appointments");
        return "staff/appointments";
    }

    @GetMapping("/appointmentsByPatientId")
    public String appointmentsByPatientId(@RequestParam(required = false) Integer patientId,
                                          HttpSession session, Model model) {
        if (isUnauthorized(session)) return "redirect:/login";
        if (patientId != null) {
            model.addAttribute("appointments",
                    apiService.getAppointmentsByPatientId(getToken(session), patientId));
            model.addAttribute("searchId", patientId);
        }
        model.addAttribute("role", session.getAttribute("role"));
        model.addAttribute("userEmail", session.getAttribute("userEmail"));
        model.addAttribute("activeTab", "appointmentsByPatientId");
        return "staff/appointmentsByPatientId";
    }

    @GetMapping("/procedure/all")
    public String allProcedures(HttpSession session, Model model) {
        if (isUnauthorized(session)) return "redirect:/login";
        model.addAttribute("procedures", apiService.getAllProcedures(getToken(session)));
        model.addAttribute("role", session.getAttribute("role"));
        model.addAttribute("userEmail", session.getAttribute("userEmail"));
        model.addAttribute("activeTab", "procedures");
        return "staff/procedures";
    }

    @GetMapping("/stayOfPatient")
    public String stayOfPatient(@RequestParam(required = false) Integer patientId,
                                HttpSession session, Model model) {
        if (isUnauthorized(session)) return "redirect:/login";
        if (patientId != null) {
            model.addAttribute("stays",
                    apiService.getStayOfPatient(getToken(session), patientId));
            model.addAttribute("searchId", patientId);
        }
        model.addAttribute("role", session.getAttribute("role"));
        model.addAttribute("userEmail", session.getAttribute("userEmail"));
        model.addAttribute("activeTab", "stayOfPatient");
        return "staff/stayOfPatient";
    }

    @GetMapping("/physicianTrainedIn")
    public String physicianTrainedIn(@RequestParam(required = false) Integer physicianId,
                                     HttpSession session, Model model) {
        if (isUnauthorized(session)) return "redirect:/login";
        if (physicianId != null) {
            model.addAttribute("trainings",
                    apiService.getPhysicianTrainedIn(getToken(session), physicianId));
            model.addAttribute("searchId", physicianId);
        }
        model.addAttribute("role", session.getAttribute("role"));
        model.addAttribute("userEmail", session.getAttribute("userEmail"));
        model.addAttribute("activeTab", "physicianTrainedIn");
        return "staff/physicianTrainedIn";
    }

    @GetMapping("/register")
    public String registerPage(HttpSession session, Model model) {
        model.addAttribute("activeTab", "register");
        return "staff/register";
    }

    @PostMapping("/register")
    public String doRegister(@ModelAttribute StaffRegisterRequest req,
                             Model model) {
        String result = apiService.registerStaff(req);
        model.addAttribute("message", result);
        model.addAttribute("activeTab", "register");
        return "staff/register";
    }
}