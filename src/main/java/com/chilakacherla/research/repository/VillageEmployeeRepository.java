package com.chilakacherla.research.repository;

import com.chilakacherla.research.model.VillageEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VillageEmployeeRepository extends JpaRepository<VillageEmployee, Long> {
    List<VillageEmployee> findByEmployeeTypeIgnoreCase(String employeeType);
}
