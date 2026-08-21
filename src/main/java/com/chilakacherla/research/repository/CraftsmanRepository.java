package com.chilakacherla.research.repository;

import com.chilakacherla.research.model.Craftsman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CraftsmanRepository extends JpaRepository<Craftsman, Long> {
    List<Craftsman> findByTradeIgnoreCase(String trade);
}
