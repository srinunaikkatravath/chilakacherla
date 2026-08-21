package com.chilakacherla.research.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "job_records")
public class JobRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String jobTitle;

    @Column(nullable = false)
    private String organization;

    private String jobType; // Government, Private, Internship
    private String location;
    private String qualification;
    private String skills;
    private LocalDate deadline;
    private String applyLink;
    private String source;
    private LocalDate postedDate;
    private LocalDate lastVerifiedDate;
    private Boolean expired = false;

    public JobRecord() {}

    public JobRecord(String jobTitle, String organization, String jobType, String location,
                     String qualification, String skills, LocalDate deadline, String applyLink,
                     String source, LocalDate postedDate) {
        this.jobTitle = jobTitle;
        this.organization = organization;
        this.jobType = jobType;
        this.location = location;
        this.qualification = qualification;
        this.skills = skills;
        this.deadline = deadline;
        this.applyLink = applyLink;
        this.source = source;
        this.postedDate = postedDate;
        this.lastVerifiedDate = LocalDate.now();
        this.expired = deadline != null && deadline.isBefore(LocalDate.now());
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

    public String getOrganization() { return organization; }
    public void setOrganization(String organization) { this.organization = organization; }

    public String getJobType() { return jobType; }
    public void setJobType(String jobType) { this.jobType = jobType; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getQualification() { return qualification; }
    public void setQualification(String qualification) { this.qualification = qualification; }

    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }

    public LocalDate getDeadline() { return deadline; }
    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
        if (deadline != null) {
            this.expired = deadline.isBefore(LocalDate.now());
        }
    }

    public String getApplyLink() { return applyLink; }
    public void setApplyLink(String applyLink) { this.applyLink = applyLink; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public LocalDate getPostedDate() { return postedDate; }
    public void setPostedDate(LocalDate postedDate) { this.postedDate = postedDate; }

    public LocalDate getLastVerifiedDate() { return lastVerifiedDate; }
    public void setLastVerifiedDate(LocalDate lastVerifiedDate) { this.lastVerifiedDate = lastVerifiedDate; }

    public Boolean getExpired() { return expired; }
    public void setExpired(Boolean expired) { this.expired = expired; }
}
