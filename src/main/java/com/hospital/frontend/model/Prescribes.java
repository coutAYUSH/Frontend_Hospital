package com.hospital.frontend.model;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Prescribes {
    private Object physician;
    private Object patient;
    private String medication;
    private String date;
    private Object appointment;
    private String dose;

    public Object getPhysician() { return physician; }
    public void setPhysician(Object physician) { this.physician = physician; }
    public Object getPatient() { return patient; }
    public void setPatient(Object patient) { this.patient = patient; }
    public String getMedication() { return medication; }
    public void setMedication(String medication) { this.medication = medication; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public Object getAppointment() { return appointment; }
    public void setAppointment(Object appointment) { this.appointment = appointment; }
    public String getDose() { return dose; }
    public void setDose(String dose) { this.dose = dose; }
}