package com.chilakacherla.research.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "scheme_applications")
public class SchemeApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String applicationId; // CHK-SCH-5001

    @Column(nullable = false)
    private String schemeName;

    @Column(nullable = false)
    private String applicantName;

    @Column(nullable = false)
    private String applicantAadhar;

    @Column(nullable = false)
    private String phone;

    private String rationCardNo;
    private String status; // UNDER_VERIFICATION, SANCTIONED, DOCUMENTS_REQUIRED
    private LocalDateTime appliedAt;

    public SchemeApplication() {}

    public SchemeApplication(String applicationId, String schemeName, String applicantName, String applicantAadhar, String phone, String rationCardNo) {
        this.applicationId = applicationId;
        this.schemeName = schemeName;
        this.applicantName = applicantName;
        this.applicantAadhar = applicantAadhar;
        this.phone = phone;
        this.rationCardNo = rationCardNo;
        this.status = "UNDER_VERIFICATION";
        this.appliedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getApplicationId() { return applicationId; }
    public void setApplicationId(String applicationId) { this.applicationId = applicationId; }

    public String getSchemeName() { return schemeName; }
    public void setSchemeName(String schemeName) { this.schemeName = schemeName; }

    public String getApplicantName() { return applicantName; }
    public void setApplicantName(String applicantName) { this.applicantName = applicantName; }

    public String getApplicantAadhar() { return applicantAadhar; }
    public void setApplicantAadhar(String applicantAadhar) { this.applicantAadhar = applicantAadhar; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getRationCardNo() { return rationCardNo; }
    public void setRationCardNo(String rationCardNo) { this.rationCardNo = rationCardNo; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getAppliedAt() { return appliedAt; }
    public void setAppliedAt(LocalDateTime appliedAt) { this.appliedAt = appliedAt; }
}
