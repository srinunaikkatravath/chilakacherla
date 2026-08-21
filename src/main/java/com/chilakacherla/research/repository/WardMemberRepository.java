package com.chilakacherla.research.repository;

import com.chilakacherla.research.model.WardMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardMemberRepository extends JpaRepository<WardMember, Long> {
    List<WardMember> findByWardNo(Integer wardNo);
}
