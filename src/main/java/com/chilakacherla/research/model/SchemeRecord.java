package com.chilakacherla.research.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "scheme_records")
public class SchemeRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String schemeName;

    @Column(nullable = false)
    private String category; // AGRICULTURE, EDUCATION, HEALTH, HOUSING, PENSION, WOMEN_EMPOWERMENT

    private String governmentLevel; // ANDHRA_PRADESH_STATE, CENTRAL_GOVT, GRAM_PANCHAYAT

    @Column(nullable = false)
    private String financialBenefit; // e.g., "₹13,500 per year"

    @Column(columnDefinition = "TEXT")
    private String eligibilityCriteria;

    @Column(columnDefinition = "TEXT")
    private String requiredDocuments;

    private String applicationStartDate;
    private String applicationDeadline;
    private String applyUrl;

    @Column(nullable = false)
    private String status; // OPEN, CLOSING_SOON, CLOSED

    private Integer beneficiaryCount; // Sanctioned beneficiaries in Chilakacherla
    private String contactOfficial;

    public SchemeRecord() {}

    public SchemeRecord(String schemeName, String category, String governmentLevel, String financialBenefit,
                        String eligibilityCriteria, String requiredDocuments, String applicationStartDate,
                        String applicationDeadline, String applyUrl, String status, Integer beneficiaryCount,
                        String contactOfficial) {
        this.schemeName = schemeName;
        this.category = category;
        this.governmentLevel = governmentLevel;
        this.financialBenefit = financialBenefit;
        this.eligibilityCriteria = eligibilityCriteria;
        this.requiredDocuments = requiredDocuments;
        this.applicationStartDate = applicationStartDate;
        this.applicationDeadline = applicationDeadline;
        this.applyUrl = applyUrl;
        this.status = status;
        this.beneficiaryCount = beneficiaryCount;
        this.contactOfficial = contactOfficial;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSchemeName() { return schemeName; }
    public void setSchemeName(String schemeName) { this.schemeName = schemeName; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getGovernmentLevel() { return governmentLevel; }
    public void setGovernmentLevel(String governmentLevel) { this.governmentLevel = governmentLevel; }

    public String getFinancialBenefit() { return financialBenefit; }
    public void setFinancialBenefit(String financialBenefit) { this.financialBenefit = financialBenefit; }

    public String getEligibilityCriteria() { return eligibilityCriteria; }
    public void setEligibilityCriteria(String eligibilityCriteria) { this.eligibilityCriteria = eligibilityCriteria; }

    public String getRequiredDocuments() { return requiredDocuments; }
    public void setRequiredDocuments(String requiredDocuments) { this.requiredDocuments = requiredDocuments; }

    public String getApplicationStartDate() { return applicationStartDate; }
    public void setApplicationStartDate(String applicationStartDate) { this.applicationStartDate = applicationStartDate; }

    public String getApplicationDeadline() { return applicationDeadline; }
    public void setApplicationDeadline(String applicationDeadline) { this.applicationDeadline = applicationDeadline; }

    public String getApplyUrl() { return applyUrl; }
    public void setApplyUrl(String applyUrl) { this.applyUrl = applyUrl; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getBeneficiaryCount() { return beneficiaryCount; }
    public void setBeneficiaryCount(Integer beneficiaryCount) { this.beneficiaryCount = beneficiaryCount; }

    public String getContactOfficial() { return contactOfficial; }
    public void setContactOfficial(String contactOfficial) { this.contactOfficial = contactOfficial; }
}
