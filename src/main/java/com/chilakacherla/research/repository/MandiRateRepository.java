package com.chilakacherla.research.repository;

import com.chilakacherla.research.model.MandiRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MandiRateRepository extends JpaRepository<MandiRate, Long> {
}
