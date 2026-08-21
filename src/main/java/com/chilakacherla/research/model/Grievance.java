package com.chilakacherla.research.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "community_grievances")
public class Grievance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String trackingId;

    private String category; // Streetlights, Water Supply, Drainage, Garbage, Roads
    private String residentName;
    private String residentPhone;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String location;
    private String status; // SUBMITTED, ASSIGNED, IN_PROGRESS, RESOLVED
    private String assignedOfficial;
    private LocalDate submittedAt;
    private LocalDate resolvedAt;

    @Column(columnDefinition = "TEXT")
    private String resolutionNotes;

    public Grievance() {}

    public Grievance(String trackingId, String category, String residentName, String residentPhone,
                     String description, String location) {
        this.trackingId = trackingId;
        this.category = category;
        this.residentName = residentName;
        this.residentPhone = residentPhone;
        this.description = description;
        this.location = location;
        this.status = "SUBMITTED";
        this.submittedAt = LocalDate.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTrackingId() { return trackingId; }
    public void setTrackingId(String trackingId) { this.trackingId = trackingId; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getResidentName() { return residentName; }
    public void setResidentName(String residentName) { this.residentName = residentName; }

    public String getResidentPhone() { return residentPhone; }
    public void setResidentPhone(String residentPhone) { this.residentPhone = residentPhone; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getAssignedOfficial() { return assignedOfficial; }
    public void setAssignedOfficial(String assignedOfficial) { this.assignedOfficial = assignedOfficial; }

    public LocalDate getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(LocalDate submittedAt) { this.submittedAt = submittedAt; }

    public LocalDate getResolvedAt() { return resolvedAt; }
    public void setResolvedAt(LocalDate resolvedAt) { this.resolvedAt = resolvedAt; }

    public String getResolutionNotes() { return resolutionNotes; }
    public void setResolutionNotes(String resolutionNotes) { this.resolutionNotes = resolutionNotes; }
}
