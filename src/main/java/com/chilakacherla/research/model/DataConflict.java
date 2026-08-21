package com.chilakacherla.research.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "data_conflicts")
public class DataConflict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String propertyName;
    private Long recordAId;
    private String sourceAName;
    private String sourceAValue;
    private LocalDate sourceADate;
    @Enumerated(EnumType.STRING)
    private TrustLevel sourceATrustLevel;

    private Long recordBId;
    private String sourceBName;
    private String sourceBValue;
    private LocalDate sourceBDate;
    @Enumerated(EnumType.STRING)
    private TrustLevel sourceBTrustLevel;

    private Boolean resolved = false;
    private String adminDecision;
    private Long selectedRecordId;

    public DataConflict() {}

    public DataConflict(String propertyName, Long recordAId, String sourceAName, String sourceAValue, LocalDate sourceADate, TrustLevel sourceATrustLevel,
                        Long recordBId, String sourceBName, String sourceBValue, LocalDate sourceBDate, TrustLevel sourceBTrustLevel) {
        this.propertyName = propertyName;
        this.recordAId = recordAId;
        this.sourceAName = sourceAName;
        this.sourceAValue = sourceAValue;
        this.sourceADate = sourceADate;
        this.sourceATrustLevel = sourceATrustLevel;
        this.recordBId = recordBId;
        this.sourceBName = sourceBName;
        this.sourceBValue = sourceBValue;
        this.sourceBDate = sourceBDate;
        this.sourceBTrustLevel = sourceBTrustLevel;
        this.resolved = false;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPropertyName() { return propertyName; }
    public void setPropertyName(String propertyName) { this.propertyName = propertyName; }

    public Long getRecordAId() { return recordAId; }
    public void setRecordAId(Long recordAId) { this.recordAId = recordAId; }

    public String getSourceAName() { return sourceAName; }
    public void setSourceAName(String sourceAName) { this.sourceAName = sourceAName; }

    public String getSourceAValue() { return sourceAValue; }
    public void setSourceAValue(String sourceAValue) { this.sourceAValue = sourceAValue; }

    public LocalDate getSourceADate() { return sourceADate; }
    public void setSourceADate(LocalDate sourceADate) { this.sourceADate = sourceADate; }

    public TrustLevel getSourceATrustLevel() { return sourceATrustLevel; }
    public void setSourceATrustLevel(TrustLevel sourceATrustLevel) { this.sourceATrustLevel = sourceATrustLevel; }

    public Long getRecordBId() { return recordBId; }
    public void setRecordBId(Long recordBId) { this.recordBId = recordBId; }

    public String getSourceBName() { return sourceBName; }
    public void setSourceBName(String sourceBName) { this.sourceBName = sourceBName; }

    public String getSourceBValue() { return sourceBValue; }
    public void setSourceBValue(String sourceBValue) { this.sourceBValue = sourceBValue; }

    public LocalDate getSourceBDate() { return sourceBDate; }
    public void setSourceBDate(LocalDate sourceBDate) { this.sourceBDate = sourceBDate; }

    public TrustLevel getSourceBTrustLevel() { return sourceBTrustLevel; }
    public void setSourceBTrustLevel(TrustLevel sourceBTrustLevel) { this.sourceBTrustLevel = sourceBTrustLevel; }

    public Boolean getResolved() { return resolved; }
    public void setResolved(Boolean resolved) { this.resolved = resolved; }

    public String getAdminDecision() { return adminDecision; }
    public void setAdminDecision(String adminDecision) { this.adminDecision = adminDecision; }

    public Long getSelectedRecordId() { return selectedRecordId; }
    public void setSelectedRecordId(Long selectedRecordId) { this.selectedRecordId = selectedRecordId; }
}
