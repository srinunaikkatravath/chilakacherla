package com.chilakacherla.research.repository;

import com.chilakacherla.research.model.JobAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobAlertRepository extends JpaRepository<JobAlert, Long> {
}
