package com.chilakacherla.research.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class NriContributor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String countryLocation; // USA (Texas), UK (London), UAE (Dubai), Hyderabad, Bengaluru
    private String nativeHabitation; // Chilakacherla, Yeguva Cherlo Palle
    private String projectSponsored; // RO Water Plant, Digital School Classroom, Solar Streetlights
    private Double contributionAmount;
    private String status; // COMPLETED, IN_PROGRESS

    public NriContributor() {}

    public NriContributor(String name, String countryLocation, String nativeHabitation, String projectSponsored, Double contributionAmount, String status) {
        this.name = name;
        this.countryLocation = countryLocation;
        this.nativeHabitation = nativeHabitation;
        this.projectSponsored = projectSponsored;
        this.contributionAmount = contributionAmount;
        this.status = status;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCountryLocation() { return countryLocation; }
    public void setCountryLocation(String countryLocation) { this.countryLocation = countryLocation; }
    public String getNativeHabitation() { return nativeHabitation; }
    public void setNativeHabitation(String nativeHabitation) { this.nativeHabitation = nativeHabitation; }
    public String getProjectSponsored() { return projectSponsored; }
    public void setProjectSponsored(String projectSponsored) { this.projectSponsored = projectSponsored; }
    public Double getContributionAmount() { return contributionAmount; }
    public void setContributionAmount(Double contributionAmount) { this.contributionAmount = contributionAmount; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
