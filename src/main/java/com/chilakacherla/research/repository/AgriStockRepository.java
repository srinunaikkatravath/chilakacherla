package com.chilakacherla.research.repository;

import com.chilakacherla.research.model.AgriStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgriStockRepository extends JpaRepository<AgriStock, Long> {
    List<AgriStock> findByCategory(String category);
}
