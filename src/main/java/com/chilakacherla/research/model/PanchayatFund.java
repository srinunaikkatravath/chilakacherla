package com.chilakacherla.research.model;

import jakarta.persistence.*;

@Entity
@Table(name = "panchayat_funds")
public class PanchayatFund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fundName;

    @Column(nullable = false)
    private String financialYear; // e.g. "2025-2026"

    @Column(nullable = false)
    private Double allocatedAmount; // ₹ in Lakhs or Rupees

    @Column(nullable = false)
    private Double spentAmount;

    @Column(nullable = false)
    private Double remainingAmount;

    @Column(columnDefinition = "TEXT")
    private String workDescription;

    private String status; // UTILIZING, FULLY_SPENT, APPROVED

    public PanchayatFund() {}

    public PanchayatFund(String fundName, String financialYear, Double allocatedAmount, Double spentAmount,
                         Double remainingAmount, String workDescription, String status) {
        this.fundName = fundName;
        this.financialYear = financialYear;
        this.allocatedAmount = allocatedAmount;
        this.spentAmount = spentAmount;
        this.remainingAmount = remainingAmount;
        this.workDescription = workDescription;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFundName() { return fundName; }
    public void setFundName(String fundName) { this.fundName = fundName; }

    public String getFinancialYear() { return financialYear; }
    public void setFinancialYear(String financialYear) { this.financialYear = financialYear; }

    public Double getAllocatedAmount() { return allocatedAmount; }
    public void setAllocatedAmount(Double allocatedAmount) { this.allocatedAmount = allocatedAmount; }

    public Double getSpentAmount() { return spentAmount; }
    public void setSpentAmount(Double spentAmount) { this.spentAmount = spentAmount; }

    public Double getRemainingAmount() { return remainingAmount; }
    public void setRemainingAmount(Double remainingAmount) { this.remainingAmount = remainingAmount; }

    public String getWorkDescription() { return workDescription; }
    public void setWorkDescription(String workDescription) { this.workDescription = workDescription; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getUtilizationPercentage() {
        if (allocatedAmount == null || allocatedAmount == 0) return 0;
        return (int) Math.round((spentAmount / allocatedAmount) * 100.0);
    }
}
