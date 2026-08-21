package com.chilakacherla.research.repository;

import com.chilakacherla.research.model.Grievance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GrievanceRepository extends JpaRepository<Grievance, Long> {
    Optional<Grievance> findByTrackingId(String trackingId);
    List<Grievance> findByStatus(String status);
}
