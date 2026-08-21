package com.chilakacherla.research.repository;

import com.chilakacherla.research.model.NriContributor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NriContributorRepository extends JpaRepository<NriContributor, Long> {
}
