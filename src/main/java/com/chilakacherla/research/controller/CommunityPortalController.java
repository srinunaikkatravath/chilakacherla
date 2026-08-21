package com.chilakacherla.research.controller;

import com.chilakacherla.research.model.*;
import com.chilakacherla.research.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/community")
@CrossOrigin(origins = "*")
public class CommunityPortalController {

    @Autowired
    private CommunityService communityService;

    // Grievance Endpoints
    @PostMapping("/grievances")
    public Grievance submitGrievance(@RequestBody Map<String, String> payload) {
        String category = payload.getOrDefault("category", "General");
        String residentName = payload.getOrDefault("residentName", "Anonymous Resident");
        String residentPhone = payload.getOrDefault("residentPhone", "N/A");
        String description = payload.getOrDefault("description", "No description provided");
        String location = payload.getOrDefault("location", "Chilakacherla");

        return communityService.submitGrievance(category, residentName, residentPhone, description, location);
    }

    @GetMapping("/grievances/{trackingId}")
    public Grievance trackGrievance(@PathVariable String trackingId) {
        return communityService.getGrievanceByTrackingId(trackingId)
                .orElseThrow(() -> new RuntimeException("Grievance tracking ID not found: " + trackingId));
    }

    @GetMapping("/grievances")
    public List<Grievance> getAllGrievances() {
        return communityService.getAllGrievances();
    }

    // Marketplace Endpoints
    @GetMapping("/marketplace")
    public List<MarketListing> getMarketplace(@RequestParam(required = false) String type) {
        return communityService.getMarketListings(type);
    }

    @PostMapping("/marketplace")
    public MarketListing createListing(@RequestBody Map<String, String> payload) {
        String type = payload.getOrDefault("listingType", "Produce");
        String title = payload.getOrDefault("title", "Marketplace Item");
        String price = payload.getOrDefault("price", "Contact for price");
        String sellerName = payload.getOrDefault("sellerName", "Resident Seller");
        String sellerPhone = payload.getOrDefault("sellerPhone", "N/A");
        String location = payload.getOrDefault("location", "Chilakacherla Village");
        String description = payload.getOrDefault("description", "");

        return communityService.createMarketListing(type, title, price, sellerName, sellerPhone, location, description);
    }

    // Blood Donor Endpoints
    @GetMapping("/blood-donors")
    public List<BloodDonor> getBloodDonors(@RequestParam(required = false) String group) {
        return communityService.getBloodDonors(group);
    }

    @PostMapping("/blood-donors")
    public BloodDonor registerDonor(@RequestBody Map<String, Object> payload) {
        String name = (String) payload.getOrDefault("name", "Volunteer Donor");
        String bloodGroup = (String) payload.getOrDefault("bloodGroup", "O+");
        String phone = (String) payload.getOrDefault("phone", "N/A");
        Integer age = payload.containsKey("age") ? Integer.parseInt(payload.get("age").toString()) : 25;
        String locality = (String) payload.getOrDefault("locality", "Chilakacherla");

        return communityService.registerBloodDonor(name, bloodGroup, phone, age, locality);
    }

    // RBK Stocks Endpoint
    @GetMapping("/rbk-stock")
    public List<AgriStock> getAgriStocks() {
        return communityService.getAgriStocks();
    }

    // Community Notices Endpoint
    @GetMapping("/notices")
    public List<CommunityNotice> getNotices() {
        return communityService.getNotices();
    }

    // Craftsmen Endpoint
    @GetMapping("/craftsmen")
    public List<Craftsman> getCraftsmen(@RequestParam(required = false) String trade) {
        return communityService.getCraftsmen(trade);
    }

    // Schemes Endpoints
    @GetMapping("/schemes")
    public List<SchemeRecord> getSchemes(@RequestParam(required = false) String category) {
        return communityService.getSchemes(category);
    }

    @PostMapping("/schemes/apply")
    public SchemeApplication submitSchemeApp(@RequestBody Map<String, String> payload) {
        String schemeName = payload.getOrDefault("schemeName", "General Scheme");
        String applicantName = payload.getOrDefault("applicantName", "Resident");
        String aadhar = payload.getOrDefault("applicantAadhar", "N/A");
        String phone = payload.getOrDefault("phone", "N/A");
        String rationCard = payload.getOrDefault("rationCardNo", "N/A");

        return communityService.submitSchemeApplication(schemeName, applicantName, aadhar, phone, rationCard);
    }

    @GetMapping("/schemes/track/{applicationId}")
    public SchemeApplication trackSchemeApp(@PathVariable String applicationId) {
        return communityService.getSchemeApplicationStatus(applicationId)
                .orElseThrow(() -> new RuntimeException("Scheme application ID not found: " + applicationId));
    }

    // Panchayat Funds Endpoint
    @GetMapping("/funds")
    public List<PanchayatFund> getPanchayatFunds() {
        return communityService.getPanchayatFunds();
    }

    // Voter Details & Ward Members Endpoints
    @GetMapping("/voters")
    public List<VoterRecord> getVoterRecords() {
        return communityService.getVoterRecords();
    }

    @GetMapping("/ward-members")
    public List<WardMember> getWardMembers() {
        return communityService.getWardMembers();
    }

    @GetMapping("/voter-members")
    public List<VoterMember> getVoterMembers(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) Integer wardNo) {
        return communityService.getVoterMembers(query, wardNo);
    }

    // Job Holders & Educated Candidates Endpoints
    @GetMapping("/job-holders")
    public List<VillageEmployee> getJobHolders(@RequestParam(required = false) String type) {
        return communityService.getVillageEmployees(type);
    }

    @GetMapping("/educated-youth")
    public List<EducatedCandidate> getEducatedYouth(@RequestParam(required = false) String status) {
        return communityService.getEducatedCandidates(status);
    }
}
