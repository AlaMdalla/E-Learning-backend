package com.example.pidevfinal.DTO;

import java.util.Date;

public class CandidateRequest {
    private String phone;
    private String resumeUrl;
    private Date applicationDate;
    private String status;
    private Long jobId; // Job ID only

    // Constructor
    public CandidateRequest() {}

    // Getters
    public String getPhone() {
        return phone;
    }

    public String getResumeUrl() {
        return resumeUrl;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public String getStatus() {
        return status;
    }

    public Long getJobId() {
        return jobId;
    }

    // Setters
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setResumeUrl(String resumeUrl) {
        this.resumeUrl = resumeUrl;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }
}
