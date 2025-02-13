package com.example.pidevfinal.repository;

import com.example.pidevfinal.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
