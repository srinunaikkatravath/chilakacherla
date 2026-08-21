package com.chilakacherla.research.controller;

import com.chilakacherla.research.model.*;
import com.chilakacherla.research.repository.*;
import com.chilakacherla.research.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/research")
@CrossOrigin(origins = "*")
public class ResearchAdminController {

    @Autowired
    private ResearchRecordRepository recordRepository;

    @Autowired
    private ResearchEngineService researchEngineService;

    @Autowired
    private AiResearchAgentService aiResearchAgentService;

    @Autowired
    private SchedulerService schedulerService;

    @Autowired
    private DataConflictRepository conflictRepository;

    @Autowired
    private DuplicateGroupRepository duplicateRepository;

    @GetMapping("/records")
    public List<ResearchRecord> getRecordsByStatus(@RequestParam(defaultValue = "PENDING") String status) {
        try {
            VerificationStatus verStatus = VerificationStatus.valueOf(status.toUpperCase());
            return recordRepository.findByVerificationStatus(verStatus);
        } catch (Exception e) {
            return recordRepository.findAll();
        }
    }

    @PostMapping("/approve/{id}")
    public ResearchRecord approveRecord(@PathVariable Long id, @RequestParam(defaultValue = "Admin") String adminUser) {
        return researchEngineService.approveRecord(id, adminUser);
    }

    @PostMapping("/reject/{id}")
    public ResearchRecord rejectRecord(@PathVariable Long id, @RequestParam(defaultValue = "Admin") String adminUser) {
        return researchEngineService.rejectRecord(id, adminUser);
    }

    @GetMapping("/conflicts")
    public List<DataConflict> getConflicts(@RequestParam(required = false) Boolean resolved) {
        if (resolved != null && resolved) {
            return conflictRepository.findByResolvedTrue();
        }
        return conflictRepository.findByResolvedFalse();
    }

    @PostMapping("/conflicts/resolve")
    public DataConflict resolveConflict(@RequestParam Long conflictId,
                                         @RequestParam Long selectedRecordId,
                                         @RequestParam String decision) {
        return researchEngineService.resolveConflict(conflictId, selectedRecordId, decision);
    }

    @GetMapping("/duplicates")
    public List<DuplicateGroup> getDuplicates() {
        return duplicateRepository.findAll();
    }

    @PostMapping("/duplicates/confirm")
    public DuplicateGroup confirmDuplicateMerge(@RequestParam Long duplicateGroupId, @RequestParam String action) {
        DuplicateGroup group = duplicateRepository.findById(duplicateGroupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));
        group.setStatus(action.toUpperCase());
        return duplicateRepository.save(group);
    }

    @PostMapping("/agent/run")
    public Map<String, Object> runAiAgent(@RequestParam String queryTopic, @RequestParam String category) {
        return aiResearchAgentService.runAgentResearch(queryTopic, category);
    }

    @GetMapping("/scheduler")
    public List<ResearchSchedule> getSchedules() {
        return schedulerService.getAllSchedules();
    }

    @PostMapping("/scheduler/trigger")
    public Map<String, Object> triggerCrawl() {
        schedulerService.runScheduleCheck();
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "SUCCESS");
        response.put("message", "Scheduled internet crawl & job expiration check completed.");
        response.put("schedules", schedulerService.getAllSchedules());
        return response;
    }
}
