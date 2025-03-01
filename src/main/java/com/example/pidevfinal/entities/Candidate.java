package com.example.pidevfinal.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String phone;

        @Column(columnDefinition = "MEDIUMTEXT")
    private String resumeUrl;

    private Date applicationDate;
    private String status;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getResumeUrl() { return resumeUrl; }
    public void setResumeUrl(String resumeUrl) { this.resumeUrl = resumeUrl; }
    public Date getApplicationDate() { return applicationDate; }
    public void setApplicationDate(Date applicationDate) { this.applicationDate = applicationDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Job getJob() { return job; }
    public void setJob(Job job) { this.job = job; }
    public Long getJobId() { return (this.job != null) ? this.job.getJobId() : null; }
}