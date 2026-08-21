package com.chilakacherla.research.repository;

import com.chilakacherla.research.model.DataConflict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataConflictRepository extends JpaRepository<DataConflict, Long> {
    List<DataConflict> findByResolvedFalse();
    List<DataConflict> findByResolvedTrue();
}
