package com.example.pidevfinal.DTO;

import java.util.Date;

public class CandidateResponse {
    private Long id;
    private String email;
    private String phone;
    private String resumeUrl;
    private Date applicationDate;
    private String status;
    private Long jobId;
    private String jobTitle;

    public CandidateResponse(Long id, String email, String phone, String resumeUrl, Date applicationDate, String status, Long jobId, String jobTitle) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.resumeUrl = resumeUrl;
        this.applicationDate = applicationDate;
        this.status = status;
        this.jobId = jobId;
        this.jobTitle = jobTitle;
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getResumeUrl() { return resumeUrl; }
    public Date getApplicationDate() { return applicationDate; }
    public String getStatus() { return status; }
    public Long getJobId() { return jobId; }
    public String getJobTitle() { return jobTitle; }

    public void setResumeUrl(String resumeUrl) { this.resumeUrl = resumeUrl; }
}