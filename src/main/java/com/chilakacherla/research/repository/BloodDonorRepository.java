package com.chilakacherla.research.repository;

import com.chilakacherla.research.model.BloodDonor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BloodDonorRepository extends JpaRepository<BloodDonor, Long> {
    List<BloodDonor> findByAvailableTrue();
    List<BloodDonor> findByBloodGroupIgnoreCaseAndAvailableTrue(String bloodGroup);
}
