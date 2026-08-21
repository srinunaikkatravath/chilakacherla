package com.chilakacherla.research.controller;

import com.chilakacherla.research.model.*;
import com.chilakacherla.research.service.ResearchEngineService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/research")
@CrossOrigin(origins = "*")
public class DataIngestionController {

    @Autowired
    private ResearchEngineService researchEngineService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/ingest")
    public ResearchRecord ingestData(@RequestBody Map<String, Object> payload) {
        String entity = (String) payload.getOrDefault("entity", "Chilakacherla");
        String categoryStr = (String) payload.getOrDefault("category", "GOVERNMENT");
        String title = (String) payload.getOrDefault("title", "Ingested Information Record");
        
        Object dataObj = payload.get("data");
        String dataJson = "";
        try {
            dataJson = objectMapper.writeValueAsString(dataObj);
        } catch (Exception e) {
            dataJson = dataObj != null ? dataObj.toString() : "{}";
        }

        Map<String, Object> sourceMap = (Map<String, Object>) payload.get("source");
        String sourceName = "Public Web Discovery";
        String sourceUrl = "https://ap.gov.in";
        String sourceType = "Public Page";

        if (sourceMap != null) {
            sourceName = (String) sourceMap.getOrDefault("name", sourceName);
            sourceUrl = (String) sourceMap.getOrDefault("url", sourceUrl);
            sourceType = (String) sourceMap.getOrDefault("type", sourceType);
        }

        Category category;
        try {
            category = Category.valueOf(categoryStr.toUpperCase());
        } catch (Exception e) {
            category = Category.GOVERNMENT;
        }

        return researchEngineService.ingestRecord(entity, category, title, dataJson, sourceUrl, sourceName, sourceType);
    }
}
