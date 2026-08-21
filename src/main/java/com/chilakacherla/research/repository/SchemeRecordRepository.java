package com.chilakacherla.research.repository;

import com.chilakacherla.research.model.SchemeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchemeRecordRepository extends JpaRepository<SchemeRecord, Long> {
    List<SchemeRecord> findByCategoryIgnoreCase(String category);
    List<SchemeRecord> findByStatusIgnoreCase(String status);
}
