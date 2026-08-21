package com.chilakacherla.research.repository;

import com.chilakacherla.research.model.ResearchSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResearchScheduleRepository extends JpaRepository<ResearchSchedule, Long> {
    List<ResearchSchedule> findByStatus(String status);
}
