package com.chilakacherla.research.service;

import com.chilakacherla.research.model.*;
import com.chilakacherla.research.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class ResearchEngineService {

    @Autowired
    private ResearchRecordRepository recordRepository;

    @Autowired
    private DataConflictRepository conflictRepository;

    @Autowired
    private DuplicateGroupRepository duplicateRepository;

    // Standardized Ingestion Pipeline: Ingest raw JSON payload
    public ResearchRecord ingestRecord(String entity, Category category, String title, String dataJson,
                                         String sourceUrl, String sourceName, String sourceType) {
        
        // 1. Calculate Trust Level & Data Layer
        TrustLevel trustLevel = determineTrustLevel(sourceUrl, sourceType);
        DataLayer dataLayer = determineDataLayer(trustLevel);
        Integer confidenceScore = calculateConfidenceScore(trustLevel, sourceUrl);

        // 2. Default Verification Status: Govt level 1 gets high confidence, but still enters verification pipeline if new
        VerificationStatus status = (trustLevel == TrustLevel.LEVEL_1_OFFICIAL && confidenceScore >= 95) 
                ? VerificationStatus.PENDING 
                : VerificationStatus.DISCOVERED;

        ResearchRecord record = new ResearchRecord(
                entity, category, title, dataJson, sourceUrl, sourceName, sourceType,
                trustLevel, LocalDate.now(), LocalDate.now(), confidenceScore, status, dataLayer
        );

        ResearchRecord saved = recordRepository.save(record);

        // 3. Automated Duplicate Detection
        detectDuplicates(saved);

        // 4. Automated Conflict Detection against existing verified records
        detectConflicts(saved);

        return saved;
    }

    public TrustLevel determineTrustLevel(String url, String sourceType) {
        if (url != null) {
            String lowerUrl = url.toLowerCase();
            if (lowerUrl.contains(".gov.in") || lowerUrl.contains(".ap.gov.in") || lowerUrl.contains(".nic.in") ||
                lowerUrl.contains("prakasam.ap.gov.in") || lowerUrl.contains("ap.gov.in")) {
                return TrustLevel.LEVEL_1_OFFICIAL;
            } else if (lowerUrl.contains(".ac.in") || lowerUrl.contains(".edu") || lowerUrl.contains("research") || lowerUrl.contains("icar")) {
                return TrustLevel.LEVEL_2_INSTITUTION;
            } else if (lowerUrl.contains("thehindu.com") || lowerUrl.contains("eenadu.net") || lowerUrl.contains("sakshi.com") || lowerUrl.contains("deccanchronicle.com")) {
                return TrustLevel.LEVEL_3_NEWS;
            } else if (lowerUrl.contains("openstreetmap.org") || lowerUrl.contains("google.com/maps") || lowerUrl.contains("directory")) {
                return TrustLevel.LEVEL_4_DIRECTORY;
            }
        }
        if (sourceType != null && sourceType.equalsIgnoreCase("community")) {
            return TrustLevel.LEVEL_5_COMMUNITY;
        }
        return TrustLevel.LEVEL_4_DIRECTORY;
    }

    public DataLayer determineDataLayer(TrustLevel trustLevel) {
        if (trustLevel == TrustLevel.LEVEL_1_OFFICIAL) {
            return DataLayer.LAYER_1_OFFICIAL;
        } else if (trustLevel == TrustLevel.LEVEL_5_COMMUNITY) {
            return DataLayer.LAYER_3_COMMUNITY;
        } else {
            return DataLayer.LAYER_2_PUBLIC;
        }
    }

    public Integer calculateConfidenceScore(TrustLevel trustLevel, String url) {
        switch (trustLevel) {
            case LEVEL_1_OFFICIAL:
                return (url != null && url.contains("ap.gov.in")) ? 98 : 92;
            case LEVEL_2_INSTITUTION:
                return 82;
            case LEVEL_3_NEWS:
                return 78;
            case LEVEL_4_DIRECTORY:
                return 65;
            case LEVEL_5_COMMUNITY:
            default:
                return 45;
        }
    }

    private void detectDuplicates(ResearchRecord newRecord) {
        List<ResearchRecord> existingRecords = recordRepository.findAll();
        List<String> knownVariations = Arrays.asList(
                "chilakacherla", "chilaka cherla", "chilakacherla colony"
        );

        String newEntityLower = newRecord.getEntity().toLowerCase().trim();

        for (ResearchRecord existing : existingRecords) {
            if (existing.getId().equals(newRecord.getId())) continue;

            String existingEntityLower = existing.getEntity().toLowerCase().trim();

            // If same title & category but different entity name variations
            if (existing.getCategory() == newRecord.getCategory() &&
                existing.getTitle().equalsIgnoreCase(newRecord.getTitle()) &&
                !existingEntityLower.equals(newEntityLower) &&
                (knownVariations.contains(newEntityLower) || knownVariations.contains(existingEntityLower))) {

                newRecord.setIsDuplicateMatch(true);
                newRecord.setMatchedEntityGroup("Chilakacherla Entity Variant");
                recordRepository.save(newRecord);

                DuplicateGroup dupGroup = new DuplicateGroup(
                        "Variant Match: " + existing.getEntity() + " <-> " + newRecord.getEntity(),
                        existing.getId(),
                        newRecord.getId(),
                        88
                );
                duplicateRepository.save(dupGroup);
            }
        }
    }

    private void detectConflicts(ResearchRecord newRecord) {
        List<ResearchRecord> verifiedRecords = recordRepository.findByVerificationStatus(VerificationStatus.VERIFIED);
        for (ResearchRecord verified : verifiedRecords) {
            if (verified.getCategory() == newRecord.getCategory() &&
                verified.getTitle().equalsIgnoreCase(newRecord.getTitle()) &&
                !verified.getDataJson().equalsIgnoreCase(newRecord.getDataJson())) {

                newRecord.setVerificationStatus(VerificationStatus.CONFLICT);
                recordRepository.save(newRecord);

                DataConflict conflict = new DataConflict(
                        newRecord.getTitle() + " Data Disagreement",
                        verified.getId(),
                        verified.getSourceName(),
                        verified.getDataJson(),
                        verified.getRetrievedDate(),
                        verified.getTrustLevel(),
                        newRecord.getId(),
                        newRecord.getSourceName(),
                        newRecord.getDataJson(),
                        newRecord.getRetrievedDate(),
                        newRecord.getTrustLevel()
                );
                conflictRepository.save(conflict);
            }
        }
    }

    public ResearchRecord approveRecord(Long recordId, String adminUser) {
        ResearchRecord record = recordRepository.findById(recordId)
                .orElseThrow(() -> new RuntimeException("Record not found: " + recordId));
        record.setVerificationStatus(VerificationStatus.VERIFIED);
        record.setVerifiedBy(adminUser);
        record.setVerifiedAt(LocalDate.now());
        record.setLastVerifiedDate(LocalDate.now());
        return recordRepository.save(record);
    }

    public ResearchRecord rejectRecord(Long recordId, String adminUser) {
        ResearchRecord record = recordRepository.findById(recordId)
                .orElseThrow(() -> new RuntimeException("Record not found: " + recordId));
        record.setVerificationStatus(VerificationStatus.REJECTED);
        record.setVerifiedBy(adminUser);
        record.setVerifiedAt(LocalDate.now());
        return recordRepository.save(record);
    }

    public DataConflict resolveConflict(Long conflictId, Long selectedRecordId, String adminDecision) {
        DataConflict conflict = conflictRepository.findById(conflictId)
                .orElseThrow(() -> new RuntimeException("Conflict not found: " + conflictId));
        conflict.setResolved(true);
        conflict.setSelectedRecordId(selectedRecordId);
        conflict.setAdminDecision(adminDecision);

        // Approve selected record and reject unselected
        if (conflict.getRecordAId().equals(selectedRecordId)) {
            approveRecord(conflict.getRecordAId(), "Admin (Conflict Resolver)");
            rejectRecord(conflict.getRecordBId(), "Admin (Conflict Resolver)");
        } else {
            approveRecord(conflict.getRecordBId(), "Admin (Conflict Resolver)");
            rejectRecord(conflict.getRecordAId(), "Admin (Conflict Resolver)");
        }

        return conflictRepository.save(conflict);
    }
}
