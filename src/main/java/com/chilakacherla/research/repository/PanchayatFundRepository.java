package com.chilakacherla.research.repository;

import com.chilakacherla.research.model.PanchayatFund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PanchayatFundRepository extends JpaRepository<PanchayatFund, Long> {
    List<PanchayatFund> findByFinancialYear(String financialYear);
}
