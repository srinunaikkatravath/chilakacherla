package com.chilakacherla.research.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "research_schedules")
public class ResearchSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String datasetName;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private ScheduleFrequency frequency;

    private LocalDate lastChecked;
    private LocalDate nextCheck;
    private String status;
    private Integer recordsDiscoveredLastRun;

    public ResearchSchedule() {}

    public ResearchSchedule(String datasetName, Category category, ScheduleFrequency frequency, LocalDate lastChecked) {
        this.datasetName = datasetName;
        this.category = category;
        this.frequency = frequency;
        this.lastChecked = lastChecked;
        this.status = "ACTIVE";
        this.recordsDiscoveredLastRun = 0;
        calculateNextCheck();
    }

    public void calculateNextCheck() {
        if (lastChecked == null) {
            this.nextCheck = LocalDate.now();
            return;
        }
        switch (frequency) {
            case DAILY:
                this.nextCheck = lastChecked.plusDays(1);
                break;
            case WEEKLY:
                this.nextCheck = lastChecked.plusWeeks(1);
                break;
            case MONTHLY:
                this.nextCheck = lastChecked.plusMonths(1);
                break;
            case MANUALLY_VERIFIED:
            default:
                this.nextCheck = null;
                break;
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDatasetName() { return datasetName; }
    public void setDatasetName(String datasetName) { this.datasetName = datasetName; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public ScheduleFrequency getFrequency() { return frequency; }
    public void setFrequency(ScheduleFrequency frequency) { this.frequency = frequency; }

    public LocalDate getLastChecked() { return lastChecked; }
    public void setLastChecked(LocalDate lastChecked) {
        this.lastChecked = lastChecked;
        calculateNextCheck();
    }

    public LocalDate getNextCheck() { return nextCheck; }
    public void setNextCheck(LocalDate nextCheck) { this.nextCheck = nextCheck; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getRecordsDiscoveredLastRun() { return recordsDiscoveredLastRun; }
    public void setRecordsDiscoveredLastRun(Integer recordsDiscoveredLastRun) { this.recordsDiscoveredLastRun = recordsDiscoveredLastRun; }
}
