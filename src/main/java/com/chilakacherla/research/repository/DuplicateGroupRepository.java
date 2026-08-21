package com.chilakacherla.research.repository;

import com.chilakacherla.research.model.DuplicateGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DuplicateGroupRepository extends JpaRepository<DuplicateGroup, Long> {
    List<DuplicateGroup> findByStatus(String status);
}
