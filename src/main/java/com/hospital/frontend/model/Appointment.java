package com.hospital.frontend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Appointment {
    private Integer appointmentId;
    private Object patient;
    private Object prepNurse;
    private Object physician;
    private String startDateTime;
    private String endDateTime;
    private String examinationRoom;

    public Integer getAppointmentId() { return appointmentId; }
    public void setAppointmentId(Integer appointmentId) { this.appointmentId = appointmentId; }
    public Object getPatient() { return patient; }
    public void setPatient(Object patient) { this.patient = patient; }
    public Object getPrepNurse() { return prepNurse; }
    public void setPrepNurse(Object prepNurse) { this.prepNurse = prepNurse; }
    public Object getPhysician() { return physician; }
    public void setPhysician(Object physician) { this.physician = physician; }
    public String getStartDateTime() { return startDateTime; }
    public void setStartDateTime(String startDateTime) { this.startDateTime = startDateTime; }
    public String getEndDateTime() { return endDateTime; }
    public void setEndDateTime(String endDateTime) { this.endDateTime = endDateTime; }
    public String getExaminationRoom() { return examinationRoom; }
    public void setExaminationRoom(String examinationRoom) { this.examinationRoom = examinationRoom; }
}
