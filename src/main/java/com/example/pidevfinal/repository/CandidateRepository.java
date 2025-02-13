package com.example.pidevfinal.repository;

import com.example.pidevfinal.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
}
