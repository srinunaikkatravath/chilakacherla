package com.chilakacherla.research.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class JobAlert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobTitle;
    private String organization; // AP Sachivalayam, APPSC, Railway Recruitment Board, Next Afield
    private String category; // GOVT_JOB, PRIVATE_IT, INTERNSHIP, APPRENTICESHIP
    private String qualification; // B.Tech, Degree, Intermediate, SSC
    private String lastDate;
    private String applicationUrl;

    public JobAlert() {}

    public JobAlert(String jobTitle, String organization, String category, String qualification, String lastDate, String applicationUrl) {
        this.jobTitle = jobTitle;
        this.organization = organization;
        this.category = category;
        this.qualification = qualification;
        this.lastDate = lastDate;
        this.applicationUrl = applicationUrl;
    }

    public Long getId() { return id; }
    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }
    public String getOrganization() { return organization; }
    public void setOrganization(String organization) { this.organization = organization; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getQualification() { return qualification; }
    public void setQualification(String qualification) { this.qualification = qualification; }
    public String getLastDate() { return lastDate; }
    public void setLastDate(String lastDate) { this.lastDate = lastDate; }
    public String getApplicationUrl() { return applicationUrl; }
    public void setApplicationUrl(String applicationUrl) { this.applicationUrl = applicationUrl; }
}
