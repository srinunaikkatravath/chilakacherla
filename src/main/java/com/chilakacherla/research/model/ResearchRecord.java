package com.chilakacherla.research.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "research_records")
public class ResearchRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String entity; // Chilakacherla, Chilaka Cherla, Chilakacherla Gudem, etc.

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String dataJson; // Standardized internal JSON payload or key details

    private String sourceUrl;
    private String sourceName;
    private String sourceType; // .gov.in, .ap.gov.in, .nic.in, news, directory, etc.

    @Enumerated(EnumType.STRING)
    private TrustLevel trustLevel;

    private LocalDate publishedDate;
    private LocalDate retrievedDate;
    private LocalDate lastVerifiedDate;

    private Integer confidenceScore; // 0-100

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VerificationStatus verificationStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DataLayer dataLayer; // Layer 1 (Official), Layer 2 (Public), Layer 3 (Community)

    private String verifiedBy;
    private LocalDate verifiedAt;

    // Optional attributes for specific categories (Funds, Agriculture, Schemes, etc.)
    private String geographicScope;
    @Column(name = "record_year")
    private Integer year;
    private String financialYear;
    private Double approvedAmount;
    private Double releasedAmount;
    private Double spentAmount;
    private String projectStatus;
    private String documentUrl;
    private String schemeAvailability; // "Available in village" vs "Potentially applicable to residents"
    private Boolean isDuplicateMatch = false;
    private String matchedEntityGroup;

    public ResearchRecord() {}

    public ResearchRecord(String entity, Category category, String title, String dataJson,
                          String sourceUrl, String sourceName, String sourceType,
                          TrustLevel trustLevel, LocalDate publishedDate, LocalDate retrievedDate,
                          Integer confidenceScore, VerificationStatus verificationStatus, DataLayer dataLayer) {
        this.entity = entity;
        this.category = category;
        this.title = title;
        this.dataJson = dataJson;
        this.sourceUrl = sourceUrl;
        this.sourceName = sourceName;
        this.sourceType = sourceType;
        this.trustLevel = trustLevel;
        this.publishedDate = publishedDate;
        this.retrievedDate = retrievedDate;
        this.confidenceScore = confidenceScore;
        this.verificationStatus = verificationStatus;
        this.dataLayer = dataLayer;
        this.lastVerifiedDate = LocalDate.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEntity() { return entity; }
    public void setEntity(String entity) { this.entity = entity; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDataJson() { return dataJson; }
    public void setDataJson(String dataJson) { this.dataJson = dataJson; }

    public String getSourceUrl() { return sourceUrl; }
    public void setSourceUrl(String sourceUrl) { this.sourceUrl = sourceUrl; }

    public String getSourceName() { return sourceName; }
    public void setSourceName(String sourceName) { this.sourceName = sourceName; }

    public String getSourceType() { return sourceType; }
    public void setSourceType(String sourceType) { this.sourceType = sourceType; }

    public TrustLevel getTrustLevel() { return trustLevel; }
    public void setTrustLevel(TrustLevel trustLevel) { this.trustLevel = trustLevel; }

    public LocalDate getPublishedDate() { return publishedDate; }
    public void setPublishedDate(LocalDate publishedDate) { this.publishedDate = publishedDate; }

    public LocalDate getRetrievedDate() { return retrievedDate; }
    public void setRetrievedDate(LocalDate retrievedDate) { this.retrievedDate = retrievedDate; }

    public LocalDate getLastVerifiedDate() { return lastVerifiedDate; }
    public void setLastVerifiedDate(LocalDate lastVerifiedDate) { this.lastVerifiedDate = lastVerifiedDate; }

    public Integer getConfidenceScore() { return confidenceScore; }
    public void setConfidenceScore(Integer confidenceScore) { this.confidenceScore = confidenceScore; }

    public VerificationStatus getVerificationStatus() { return verificationStatus; }
    public void setVerificationStatus(VerificationStatus verificationStatus) { this.verificationStatus = verificationStatus; }

    public DataLayer getDataLayer() { return dataLayer; }
    public void setDataLayer(DataLayer dataLayer) { this.dataLayer = dataLayer; }

    public String getVerifiedBy() { return verifiedBy; }
    public void setVerifiedBy(String verifiedBy) { this.verifiedBy = verifiedBy; }

    public LocalDate getVerifiedAt() { return verifiedAt; }
    public void setVerifiedAt(LocalDate verifiedAt) { this.verifiedAt = verifiedAt; }

    public String getGeographicScope() { return geographicScope; }
    public void setGeographicScope(String geographicScope) { this.geographicScope = geographicScope; }

    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }

    public String getFinancialYear() { return financialYear; }
    public void setFinancialYear(String financialYear) { this.financialYear = financialYear; }

    public Double getApprovedAmount() { return approvedAmount; }
    public void setApprovedAmount(Double approvedAmount) { this.approvedAmount = approvedAmount; }

    public Double getReleasedAmount() { return releasedAmount; }
    public void setReleasedAmount(Double releasedAmount) { this.releasedAmount = releasedAmount; }

    public Double getSpentAmount() { return spentAmount; }
    public void setSpentAmount(Double spentAmount) { this.spentAmount = spentAmount; }

    public String getProjectStatus() { return projectStatus; }
    public void setProjectStatus(String projectStatus) { this.projectStatus = projectStatus; }

    public String getDocumentUrl() { return documentUrl; }
    public void setDocumentUrl(String documentUrl) { this.documentUrl = documentUrl; }

    public String getSchemeAvailability() { return schemeAvailability; }
    public void setSchemeAvailability(String schemeAvailability) { this.schemeAvailability = schemeAvailability; }

    public Boolean getIsDuplicateMatch() { return isDuplicateMatch; }
    public void setIsDuplicateMatch(Boolean isDuplicateMatch) { this.isDuplicateMatch = isDuplicateMatch; }

    public String getMatchedEntityGroup() { return matchedEntityGroup; }
    public void setMatchedEntityGroup(String matchedEntityGroup) { this.matchedEntityGroup = matchedEntityGroup; }
}
