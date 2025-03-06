package com.example.pidevfinal.controller;

import com.example.pidevfinal.DTO.CandidateRequest;
import com.example.pidevfinal.DTO.CandidateResponse;
import com.example.pidevfinal.services.CandidateService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowedHeaders = "*")
@RequestMapping("/api/candidates")
public class CandidateController {
    private final CandidateService candidateService;

    private static final String UPLOAD_DIR = "C:/pidev/attachments/";

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
        System.out.println("CandidateController initialized");
    }

    @GetMapping
    public List<CandidateResponse> getAllCandidates() {
        List<CandidateResponse> candidates = candidateService.getAllCandidates();
        candidates.forEach(candidate -> {
            if (candidate.getResumeUrl() != null && candidate.getResumeUrl().length() > 1000) {
                candidate.setResumeUrl(candidate.getResumeUrl().substring(0, 1000) + "...");
                System.out.println("Truncated resumeUrl for candidate " + candidate.getId());
            }
        });
        System.out.println("Returning candidates: " + candidates.size());
        return candidates;
    }

    @GetMapping("/{id}")
    public CandidateResponse getCandidateById(@PathVariable Long id) {
        return candidateService.getCandidateById(id);
    }

    @GetMapping("/{id}/resume")
    public ResponseEntity<String> getCandidateResume(@PathVariable Long id) {
        try {
            CandidateResponse candidate = candidateService.getCandidateById(id);
            if (candidate == null || candidate.getResumeUrl() == null) {
                return ResponseEntity.notFound().build();
            }
            System.out.println("Fetching resume for candidate " + id + ", URL: " + candidate.getResumeUrl());
            return ResponseEntity.ok(candidate.getResumeUrl());
        } catch (Exception e) {
            System.err.println("Error fetching resume for candidate " + id + ": " + e.getMessage());
            return ResponseEntity.status(500).body("Error fetching resume");
        }
    }

    @PostMapping
    public ResponseEntity<Void> createCandidate(@RequestBody CandidateRequest request) {
        System.out.println("Received request: " + request);
        candidateService.saveCandidate(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadResume(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("No file uploaded");
            }
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            File dest = new File(UPLOAD_DIR + fileName);
            dest.getParentFile().mkdirs(); // Create directory if it doesn’t exist
            file.transferTo(dest);
            String fileUrl = "/attachments/" + fileName; // URL relative to static serving path
            System.out.println("File uploaded: " + fileUrl);
            return ResponseEntity.ok(fileUrl);
        } catch (Exception e) {
            System.err.println("Error uploading file: " + e.getMessage());
            return ResponseEntity.status(500).body("Error uploading file: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable Long id) {
        try {
            System.out.println("Deleting candidate " + id);
            candidateService.deleteCandidate(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.err.println("Error deleting candidate " + id + ": " + e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/{id}")
    public CandidateResponse updateCandidate(@PathVariable Long id, @RequestBody CandidateRequest candidateDetails) {
        return candidateService.updateCandidate(id, candidateDetails);
    }
}