package com.chilakacherla.research.repository;

import com.chilakacherla.research.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResearchRecordRepository extends JpaRepository<ResearchRecord, Long> {

    List<ResearchRecord> findByVerificationStatus(VerificationStatus verificationStatus);

    List<ResearchRecord> findByCategoryAndVerificationStatus(Category category, VerificationStatus verificationStatus);

    List<ResearchRecord> findByDataLayerAndVerificationStatus(DataLayer dataLayer, VerificationStatus verificationStatus);

    List<ResearchRecord> findByTrustLevel(TrustLevel trustLevel);

    @Query("SELECT r FROM ResearchRecord r WHERE r.verificationStatus = :status AND " +
           "(LOWER(r.title) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(r.entity) LIKE LOWER(CONCAT('%', :query, '%')))")
    List<ResearchRecord> searchByQueryAndStatus(@Param("query") String query, @Param("status") VerificationStatus status);

    List<ResearchRecord> findByEntityContainingIgnoreCase(String entityKeyword);

    long countByVerificationStatus(VerificationStatus verificationStatus);

    long countByTrustLevel(TrustLevel trustLevel);
}
