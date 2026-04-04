package com.hospital.frontend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Stay {
    private Integer stayId;
    private String stayStart;
    private String stayEnd;
    private Object patient;
    private Object room;

    public Integer getStayId() { return stayId; }
    public void setStayId(Integer stayId) { this.stayId = stayId; }
    public String getStayStart() { return stayStart; }
    public void setStayStart(String stayStart) { this.stayStart = stayStart; }
    public String getStayEnd() { return stayEnd; }
    public void setStayEnd(String stayEnd) { this.stayEnd = stayEnd; }
    public Object getPatient() { return patient; }
    public void setPatient(Object patient) { this.patient = patient; }
    public Object getRoom() { return room; }
    public void setRoom(Object room) { this.room = room; }
}