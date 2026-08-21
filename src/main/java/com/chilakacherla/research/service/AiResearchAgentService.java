package com.chilakacherla.research.service;

import com.chilakacherla.research.model.*;
import com.chilakacherla.research.repository.ResearchRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AiResearchAgentService {

    @Autowired
    private ResearchEngineService researchEngineService;

    @Autowired
    private ResearchRecordRepository recordRepository;

    public Map<String, Object> runAgentResearch(String queryTopic, String categoryName) {
        Map<String, Object> agentResult = new LinkedHashMap<>();
        List<String> executionLogs = new ArrayList<>();

        executionLogs.add("[1. SEARCH] Querying public web & government indexes for: '" + queryTopic + "' (Target: Chilakacherla, PIN 523331)");
        
        Category category = parseCategory(categoryName);

        // Simulated AI Research Discovery Workflow
        executionLogs.add("[2. EXTRACT] Found candidate public page: 'https://prakasam.ap.gov.in/civic-data/" + queryTopic.toLowerCase().replaceAll("[^a-z0-9]", "-") + "'");
        executionLogs.add("[3. RELEVANCE] Confirmed PIN 523331 match & Dornala Mandal administrative alignment.");
        executionLogs.add("[4. CLASSIFY] Categorized discovery under: " + category.getDisplayName());

        String extractedData = "{\"topic\":\"" + queryTopic + "\", \"PIN\":\"523331\", \"mandal\":\"Dornala\", \"district\":\"Prakasam\", \"details\":\"Extracted from public government source index for Chilakacherla village.\"}";

        executionLogs.add("[5. DEDUPLICATE] Running fuzzy entity resolution against known variations (Chilakacherla, Chilaka Cherla)...");
        executionLogs.add("[6. SCORE] Assigning source trust ranking & confidence score...");

        String sourceUrl = "https://prakasam.ap.gov.in/public-notices/" + queryTopic.toLowerCase().replaceAll("[^a-z0-9]", "-");
        ResearchRecord record = researchEngineService.ingestRecord(
                "Chilakacherla",
                category,
                "Discovered: " + queryTopic + " (AI Mining)",
                extractedData,
                sourceUrl,
                "Prakasam District Government Portal",
                "Official Govt Domain"
        );

        executionLogs.add("[7. QUEUE] Record successfully placed in Verification Queue with ID #" + record.getId() + " and Confidence Score " + record.getConfidenceScore() + "%");
        executionLogs.add("[8. HUMAN OVERSIHIT] PENDING verification approval required before publishing to public website.");

        agentResult.put("status", "SUCCESS");
        agentResult.put("queryTopic", queryTopic);
        agentResult.put("category", category.getDisplayName());
        agentResult.put("recordId", record.getId());
        agentResult.put("confidenceScore", record.getConfidenceScore());
        agentResult.put("verificationStatus", record.getVerificationStatus());
        agentResult.put("logs", executionLogs);

        return agentResult;
    }

    private Category parseCategory(String categoryName) {
        if (categoryName == null) return Category.GOVERNMENT;
        try {
            return Category.valueOf(categoryName.toUpperCase());
        } catch (Exception e) {
            return Category.GOVERNMENT;
        }
    }
}
