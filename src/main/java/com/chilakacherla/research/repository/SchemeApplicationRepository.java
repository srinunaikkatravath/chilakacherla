package com.chilakacherla.research.repository;

import com.chilakacherla.research.model.SchemeApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchemeApplicationRepository extends JpaRepository<SchemeApplication, Long> {
    Optional<SchemeApplication> findByApplicationId(String applicationId);
}
