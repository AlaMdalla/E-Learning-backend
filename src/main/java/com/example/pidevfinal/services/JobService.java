package com.example.pidevfinal.services;

import com.example.pidevfinal.entities.Job;
import com.example.pidevfinal.repository.JobRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class JobService {
    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    public Job saveJob(Job job) {
        return jobRepository.save(job);
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }
    public Job updateJob(Long id, Job jobDetails) {
        return jobRepository.findById(id).map(job -> {
            job.setTitle(jobDetails.getTitle());
            job.setDepartment(jobDetails.getDepartment());
            job.setLocation(jobDetails.getLocation());
            job.setDescription(jobDetails.getDescription());
            job.setRequirements(jobDetails.getRequirements());
            job.setPostedDate(jobDetails.getPostedDate());
            job.setStatus(jobDetails.getStatus());
            return jobRepository.save(job);
        }).orElse(null);
    }

}

