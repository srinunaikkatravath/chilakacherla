package com.chilakacherla.research.repository;

import com.chilakacherla.research.model.MarketListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarketListingRepository extends JpaRepository<MarketListing, Long> {
    List<MarketListing> findByAvailableTrue();
    List<MarketListing> findByListingTypeAndAvailableTrue(String listingType);
}
