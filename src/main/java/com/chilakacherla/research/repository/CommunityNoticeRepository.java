package com.chilakacherla.research.repository;

import com.chilakacherla.research.model.CommunityNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityNoticeRepository extends JpaRepository<CommunityNotice, Long> {
    List<CommunityNotice> findByCategory(String category);
    List<CommunityNotice> findByPriority(String priority);
}
