package com.chilakacherla.research.repository;

import com.chilakacherla.research.model.VillageEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VillageEventRepository extends JpaRepository<VillageEvent, Long> {
}
