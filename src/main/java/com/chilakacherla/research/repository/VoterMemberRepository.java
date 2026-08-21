package com.chilakacherla.research.repository;

import com.chilakacherla.research.model.VoterMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoterMemberRepository extends JpaRepository<VoterMember, Long> {
    List<VoterMember> findByWardNo(Integer wardNo);

    @Query("SELECT v FROM VoterMember v WHERE LOWER(v.voterName) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(v.epicNo) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(v.houseNo) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(v.habitation) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<VoterMember> searchVoters(@Param("query") String query);
}
