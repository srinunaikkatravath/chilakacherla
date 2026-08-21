package com.chilakacherla.research.controller;

import com.chilakacherla.research.model.*;
import com.chilakacherla.research.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/public/village")
@CrossOrigin(origins = "*")
public class PublicVillageController {

    @Autowired
    private ResearchRecordRepository recordRepository;

    @Autowired
    private JobRecordRepository jobRecordRepository;

    @GetMapping("/records")
    public List<ResearchRecord> getVerifiedRecords(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String dataLayer,
            @RequestParam(required = false) String query) {

        if (query != null && !query.trim().isEmpty()) {
            return recordRepository.searchByQueryAndStatus(query.trim(), VerificationStatus.VERIFIED);
        }

        if (category != null && !category.trim().isEmpty()) {
            try {
                Category cat = Category.valueOf(category.toUpperCase());
                return recordRepository.findByCategoryAndVerificationStatus(cat, VerificationStatus.VERIFIED);
            } catch (Exception e) {
                // Ignore invalid category
            }
        }

        if (dataLayer != null && !dataLayer.trim().isEmpty()) {
            try {
                DataLayer layer = DataLayer.valueOf(dataLayer.toUpperCase());
                return recordRepository.findByDataLayerAndVerificationStatus(layer, VerificationStatus.VERIFIED);
            } catch (Exception e) {
                // Ignore invalid layer
            }
        }

        return recordRepository.findByVerificationStatus(VerificationStatus.VERIFIED);
    }

    @GetMapping("/jobs")
    public List<JobRecord> getActiveJobs(@RequestParam(required = false) String type) {
        if (type != null && !type.trim().isEmpty()) {
            return jobRecordRepository.findByJobTypeAndExpiredFalse(type);
        }
        return jobRecordRepository.findByExpiredFalse();
    }

    @GetMapping("/stats")
    public Map<String, Object> getVillageStats() {
        Map<String, Object> stats = new LinkedHashMap<>();
        stats.put("targetVillage", "Chilakacherla");
        stats.put("PIN", "523331");
        stats.put("mandal", "Dornala");
        stats.put("district", "Prakasam");
        stats.put("state", "Andhra Pradesh");

        long totalVerified = recordRepository.countByVerificationStatus(VerificationStatus.VERIFIED);
        long pendingVerification = recordRepository.countByVerificationStatus(VerificationStatus.PENDING);
        long discoveredCount = recordRepository.countByVerificationStatus(VerificationStatus.DISCOVERED);
        long conflictCount = recordRepository.countByVerificationStatus(VerificationStatus.CONFLICT);

        stats.put("totalVerifiedRecords", totalVerified);
        stats.put("pendingVerification", pendingVerification);
        stats.put("discoveredRecords", discoveredCount);
        stats.put("conflictsCount", conflictCount);

        List<ResearchRecord> verified = recordRepository.findByVerificationStatus(VerificationStatus.VERIFIED);
        long layer1Count = verified.stream().filter(r -> r.getDataLayer() == DataLayer.LAYER_1_OFFICIAL).count();
        long layer2Count = verified.stream().filter(r -> r.getDataLayer() == DataLayer.LAYER_2_PUBLIC).count();
        long layer3Count = verified.stream().filter(r -> r.getDataLayer() == DataLayer.LAYER_3_COMMUNITY).count();

        stats.put("layer1OfficialCount", layer1Count);
        stats.put("layer2PublicCount", layer2Count);
        stats.put("layer3CommunityCount", layer3Count);

        return stats;
    }

    @GetMapping("/sources")
    public Map<String, Object> getDataSourcesBreakdown() {
        Map<String, Object> response = new LinkedHashMap<>();
        List<ResearchRecord> verified = recordRepository.findByVerificationStatus(VerificationStatus.VERIFIED);

        List<Map<String, Object>> sourcesList = new ArrayList<>();
        for (ResearchRecord record : verified) {
            Map<String, Object> s = new LinkedHashMap<>();
            s.put("id", record.getId());
            s.put("category", record.getCategory().getDisplayName());
            s.put("title", record.getTitle());
            s.put("sourceName", record.getSourceName());
            s.put("sourceUrl", record.getSourceUrl());
            s.put("trustLevel", record.getTrustLevel().getDescription());
            s.put("confidenceScore", record.getConfidenceScore() + "%");
            s.put("dataLayer", record.getDataLayer().getLabel());
            s.put("lastVerifiedDate", record.getLastVerifiedDate());
            sourcesList.add(s);
        }

        response.put("totalSources", sourcesList.size());
        response.put("sources", sourcesList);
        return response;
    }
}
