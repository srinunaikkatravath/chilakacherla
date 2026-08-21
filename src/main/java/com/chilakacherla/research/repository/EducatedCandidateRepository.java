package com.chilakacherla.research.repository;

import com.chilakacherla.research.model.EducatedCandidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducatedCandidateRepository extends JpaRepository<EducatedCandidate, Long> {
    List<EducatedCandidate> findByDegreeIgnoreCase(String degree);
    List<EducatedCandidate> findByStatusIgnoreCase(String status);
}
