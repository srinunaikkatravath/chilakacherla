package com.chilakacherla.research.service;

import com.chilakacherla.research.model.*;
import com.chilakacherla.research.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CommunityService {

    @Autowired
    private GrievanceRepository grievanceRepository;

    @Autowired
    private MarketListingRepository marketRepository;

    @Autowired
    private BloodDonorRepository donorRepository;

    @Autowired
    private AgriStockRepository agriStockRepository;

    @Autowired
    private CommunityNoticeRepository noticeRepository;

    @Autowired
    private CraftsmanRepository craftsmanRepository;

    @Autowired
    private SchemeRecordRepository schemeRepository;

    @Autowired
    private PanchayatFundRepository fundRepository;

    @Autowired
    private SchemeApplicationRepository schemeApplicationRepository;

    @Autowired
    private VoterRecordRepository voterRecordRepository;

    @Autowired
    private WardMemberRepository wardMemberRepository;

    @Autowired
    private VoterMemberRepository voterMemberRepository;

    @Autowired
    private VillageEmployeeRepository villageEmployeeRepository;

    @Autowired
    private EducatedCandidateRepository educatedCandidateRepository;

    // Grievance Handling
    public Grievance submitGrievance(String category, String residentName, String residentPhone, String description, String location) {
        String trackingId = "CHK-GRV-" + (1000 + grievanceRepository.count() + 1);
        Grievance grievance = new Grievance(trackingId, category, residentName, residentPhone, description, location);
        return grievanceRepository.save(grievance);
    }

    public Optional<Grievance> getGrievanceByTrackingId(String trackingId) {
        return grievanceRepository.findByTrackingId(trackingId.toUpperCase().trim());
    }

    public List<Grievance> getAllGrievances() {
        return grievanceRepository.findAll();
    }

    // Marketplace
    public MarketListing createMarketListing(String listingType, String title, String price, String sellerName, String sellerPhone, String location, String description) {
        MarketListing listing = new MarketListing(listingType, title, price, sellerName, sellerPhone, location, description);
        return marketRepository.save(listing);
    }

    public List<MarketListing> getMarketListings(String type) {
        if (type != null && !type.trim().isEmpty() && !type.equalsIgnoreCase("ALL")) {
            return marketRepository.findByListingTypeAndAvailableTrue(type);
        }
        return marketRepository.findByAvailableTrue();
    }

    // Blood Donors
    public BloodDonor registerBloodDonor(String name, String bloodGroup, String phone, Integer age, String locality) {
        BloodDonor donor = new BloodDonor(name, bloodGroup.toUpperCase().trim(), phone, age, locality);
        return donorRepository.save(donor);
    }

    public List<BloodDonor> getBloodDonors(String group) {
        if (group != null && !group.trim().isEmpty() && !group.equalsIgnoreCase("ALL")) {
            return donorRepository.findByBloodGroupIgnoreCaseAndAvailableTrue(group);
        }
        return donorRepository.findByAvailableTrue();
    }

    // RBK Stocks
    public List<AgriStock> getAgriStocks() {
        return agriStockRepository.findAll();
    }

    // Notices
    public List<CommunityNotice> getNotices() {
        return noticeRepository.findAll();
    }

    // Craftsmen
    public List<Craftsman> getCraftsmen(String trade) {
        if (trade != null && !trade.trim().isEmpty() && !trade.equalsIgnoreCase("ALL")) {
            return craftsmanRepository.findByTradeIgnoreCase(trade);
        }
        return craftsmanRepository.findAll();
    }

    // Schemes Ecosystem
    public List<SchemeRecord> getSchemes(String category) {
        if (category != null && !category.trim().isEmpty() && !category.equalsIgnoreCase("ALL")) {
            return schemeRepository.findByCategoryIgnoreCase(category);
        }
        return schemeRepository.findAll();
    }

    public SchemeApplication submitSchemeApplication(String schemeName, String applicantName, String applicantAadhar, String phone, String rationCardNo) {
        String appId = "CHK-SCH-" + (5000 + schemeApplicationRepository.count() + 1);
        SchemeApplication app = new SchemeApplication(appId, schemeName, applicantName, applicantAadhar, phone, rationCardNo);
        return schemeApplicationRepository.save(app);
    }

    public Optional<SchemeApplication> getSchemeApplicationStatus(String applicationId) {
        return schemeApplicationRepository.findByApplicationId(applicationId.toUpperCase().trim());
    }

    // Panchayat Funds
    public List<PanchayatFund> getPanchayatFunds() {
        return fundRepository.findAll();
    }

    // Voter Records & Ward Members
    public List<VoterRecord> getVoterRecords() {
        return voterRecordRepository.findAll();
    }

    public List<WardMember> getWardMembers() {
        return wardMemberRepository.findAll();
    }

    public List<VoterMember> getVoterMembers(String query, Integer wardNo) {
        if (query != null && !query.trim().isEmpty()) {
            return voterMemberRepository.searchVoters(query.trim());
        }
        if (wardNo != null && wardNo > 0) {
            return voterMemberRepository.findByWardNo(wardNo);
        }
        return voterMemberRepository.findAll();
    }

    // Job Holders & Educated Youth
    public List<VillageEmployee> getVillageEmployees(String type) {
        if (type != null && !type.trim().isEmpty() && !type.equalsIgnoreCase("ALL")) {
            return villageEmployeeRepository.findByEmployeeTypeIgnoreCase(type);
        }
        return villageEmployeeRepository.findAll();
    }

    public List<EducatedCandidate> getEducatedCandidates(String status) {
        if (status != null && !status.trim().isEmpty() && !status.equalsIgnoreCase("ALL")) {
            return educatedCandidateRepository.findByStatusIgnoreCase(status);
        }
        return educatedCandidateRepository.findAll();
    }
}
