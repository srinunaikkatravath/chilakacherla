package com.chilakacherla.research.repository;

import com.chilakacherla.research.model.JobRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRecordRepository extends JpaRepository<JobRecord, Long> {
    List<JobRecord> findByExpiredFalse();
    List<JobRecord> findByJobTypeAndExpiredFalse(String jobType);
}
