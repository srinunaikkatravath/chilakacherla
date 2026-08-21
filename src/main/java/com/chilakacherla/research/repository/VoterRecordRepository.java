package com.chilakacherla.research.repository;

import com.chilakacherla.research.model.VoterRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoterRecordRepository extends JpaRepository<VoterRecord, Long> {
    List<VoterRecord> findByWardNo(Integer wardNo);
}
