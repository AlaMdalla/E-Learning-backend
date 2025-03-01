package com.example.pidevfinal.controller;

import com.example.pidevfinal.entities.Job;
import com.example.pidevfinal.services.JobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/jobs")
public class JobController {
    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<Job>> getAllJobs() {
        List<Job> jobs = jobService.getAllJobs();

        jobs.forEach(job -> {
            if (job.getImage() != null && job.getImage().length() > 1000) {
                job.setImage(job.getImage().substring(0, 1000) + "...");
            }
        });
        System.out.println("✅ Jobs JSON Response: " + jobs.toString());
        return ResponseEntity.ok(jobs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        return job != null ? ResponseEntity.ok(job) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<String> getJobImage(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        return job != null && job.getImage() != null ? ResponseEntity.ok(job.getImage()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> createJob(@RequestBody Job job) {
        jobService.saveJob(job);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job jobDetails) {
        Job updatedJob = jobService.updateJob(id, jobDetails);
        return updatedJob != null ? ResponseEntity.ok(updatedJob) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ResponseEntity.ok().build();
    }
}