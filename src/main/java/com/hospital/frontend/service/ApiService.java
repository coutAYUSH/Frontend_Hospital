package com.hospital.frontend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.frontend.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class ApiService {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public ApiService(RestTemplate restTemplate,
                      @Value("${backend.base-url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    // ──────────────────────────────────────────────
    // Helper
    // ──────────────────────────────────────────────
    private HttpHeaders authHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private <T> T getForObject(String path, String token, Class<T> type) {
        try {
            HttpEntity<?> entity = new HttpEntity<>(authHeaders(token));
            ResponseEntity<T> resp = restTemplate.exchange(
                    baseUrl + path, HttpMethod.GET, entity, type);
            return resp.getBody();
        } catch (HttpClientErrorException e) {
            return null;
        }
    }

    private <T> List<T> getForList(String path, String token,
                                   ParameterizedTypeReference<List<T>> ref) {
        try {
            HttpEntity<?> entity = new HttpEntity<>(authHeaders(token));
            ResponseEntity<List<T>> resp = restTemplate.exchange(
                    baseUrl + path, HttpMethod.GET, entity, ref);
            return resp.getBody() != null ? resp.getBody() : Collections.emptyList();
        } catch (HttpClientErrorException e) {
            return Collections.emptyList();
        }
    }

    // ──────────────────────────────────────────────
    // Auth
    // ──────────────────────────────────────────────
    public LoginResponse login(LoginRequest req) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LoginRequest> entity = new HttpEntity<>(req, headers);
        try {
            ResponseEntity<LoginResponse> resp = restTemplate.postForEntity(
                    baseUrl + "/auth/login", entity, LoginResponse.class);
            return resp.getBody();
        } catch (HttpClientErrorException e) {
            return null;
        }
    }

    // ──────────────────────────────────────────────
    // Nurse
    // ──────────────────────────────────────────────
    public List<Appointment> getNurseAppointments(String token) {
        return getForList("/nurse/appointments", token,
                new ParameterizedTypeReference<>() {});
    }

    public List<Appointment> getNurseOncalls(String token) {
        return getForList("/nurse/oncalls", token,
                new ParameterizedTypeReference<>() {});
    }

    public List<Nurse> getAllNurses(String token) {
        return getForList("/nurse/allnurse", token,
                new ParameterizedTypeReference<>() {});
    }

    // ──────────────────────────────────────────────
    // Physician
    // ──────────────────────────────────────────────
    public List<Patient> getPhysicianPatients(String token) {
        return getForList("/physician/patients", token,
                new ParameterizedTypeReference<>() {});
    }

    public List<Appointment> getPhysicianAppointments(String token) {
        return getForList("/physician/appointments", token,
                new ParameterizedTypeReference<>() {});
    }

    public List<Prescribes> getPhysicianPrescribes(String token) {
        return getForList("/physician/prescribes", token,
                new ParameterizedTypeReference<>() {});
    }

    // ──────────────────────────────────────────────
    // Room (API)
    // ──────────────────────────────────────────────
    public Room getRoomById(String token, Integer id) {
        return getForObject("/api/room/" + id, token, Room.class);
    }

    public List<Room> getAvailableRooms(String token) {
        return getForList("/api/availableRoom", token,
                new ParameterizedTypeReference<>() {});
    }

    // ──────────────────────────────────────────────
    // Staff
    // ──────────────────────────────────────────────
    public String registerStaff(StaffRegisterRequest req) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<StaffRegisterRequest> entity = new HttpEntity<>(req, headers);
        try {
            ResponseEntity<String> resp = restTemplate.postForEntity(
                    baseUrl + "/staff/register", entity, String.class);
            return resp.getBody();
        } catch (HttpClientErrorException e) {
            return "Error: " + e.getMessage();
        }
    }

    public List<Patient> getAllPatients(String token) {
        return getForList("/staff/allpatients", token,
                new ParameterizedTypeReference<>() {});
    }

    public List<Physician> getAllPhysicians(String token) {
        return getForList("/staff/allphysicians", token,
                new ParameterizedTypeReference<>() {});
    }

    public List<Nurse> getAllNursesStaff(String token) {
        return getForList("/staff/allnurses", token,
                new ParameterizedTypeReference<>() {});
    }

    public Patient getPatientById(String token, Integer id) {
        return getForObject("/staff/patientById/" + id, token, Patient.class);
    }

    public List<Appointment> getAppointmentsById(String token, Integer id) {
        return getForList("/staff/appointments/" + id, token,
                new ParameterizedTypeReference<>() {});
    }

    public List<Appointment> getAppointmentsByPatientId(String token, Integer patientId) {
        return getForList("/staff/appointmentsByPatientId/" + patientId, token,
                new ParameterizedTypeReference<>() {});
    }

    public List<Procedure> getAllProcedures(String token) {
        return getForList("/staff/procedure/all", token,
                new ParameterizedTypeReference<>() {});
    }

    public Object getStaffById(String token, Integer id) {
        return getForObject("/staff/" + id, token, Object.class);
    }

    public List<Stay> getStayOfPatient(String token, Integer patientId) {
        return getForList("/staff/stayOfPatient/" + patientId, token,
                new ParameterizedTypeReference<>() {});
    }

    public List<TrainedIn> getPhysicianTrainedIn(String token, Integer physicianId) {
        return getForList("/staff/physicianTrainedIn/" + physicianId, token,
                new ParameterizedTypeReference<>() {});
    }
}
