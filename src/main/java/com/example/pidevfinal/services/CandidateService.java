package com.example.pidevfinal.services;

import com.example.pidevfinal.DTO.CandidateRequest;
import com.example.pidevfinal.entities.Candidate;
import com.example.pidevfinal.entities.Job;
import com.example.pidevfinal.repository.CandidateRepository;
import com.example.pidevfinal.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {
    private final JobRepository jobRepository;
    private final CandidateRepository candidateRepository;

    @Autowired
    public CandidateService(JobRepository jobRepository, CandidateRepository candidateRepository) {
        this.jobRepository = jobRepository;
        this.candidateRepository = candidateRepository;
    }

    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }

    public Candidate getCandidateById(Long id) {
        return candidateRepository.findById(id).orElse(null);
    }

    public Candidate saveCandidate(CandidateRequest request) {
        // Fetch the job from the database
        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() -> new RuntimeException("Job not found with ID: " + request.getJobId()));

        // Create a new Candidate
        Candidate candidate = new Candidate();
        candidate.setPhone(request.getPhone());
        candidate.setResumeUrl(request.getResumeUrl());
        candidate.setApplicationDate(request.getApplicationDate());
        candidate.setStatus(request.getStatus());
        candidate.setJob(job); // Assign the job

        return candidateRepository.save(candidate);
    }

    public void deleteCandidate(Long id) {
        candidateRepository.deleteById(id);
    }

    public Candidate updateCandidate(Long id, Candidate candidateDetails) {
        return candidateRepository.findById(id).map(candidate -> {
            candidate.setPhone(candidateDetails.getPhone());
            candidate.setResumeUrl(candidateDetails.getResumeUrl());
            candidate.setApplicationDate(candidateDetails.getApplicationDate());
            candidate.setStatus(candidateDetails.getStatus());
            candidate.setJob(candidateDetails.getJob());
            return candidateRepository.save(candidate);
        }).orElse(null);
    }
}
