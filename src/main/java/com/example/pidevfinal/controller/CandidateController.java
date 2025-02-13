package com.example.pidevfinal.controller;

import com.example.pidevfinal.DTO.CandidateRequest;
import com.example.pidevfinal.entities.Candidate;
import com.example.pidevfinal.services.CandidateService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {
    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping
    public List<Candidate> getAllCandidates() {
        return candidateService.getAllCandidates();
    }

    @GetMapping("/{id}")
    public Candidate getCandidateById(@PathVariable Long id) {
        return candidateService.getCandidateById(id);
    }

    @PostMapping
    public Candidate createCandidate(@RequestBody CandidateRequest request) {
        return candidateService.saveCandidate(request);
    }



    @DeleteMapping("/{id}")
    public void deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
    }
    @PutMapping("/{id}")
    public Candidate updateCandidate(@PathVariable Long id, @RequestBody Candidate candidateDetails) {
        return candidateService.updateCandidate(id, candidateDetails);
    }

}

