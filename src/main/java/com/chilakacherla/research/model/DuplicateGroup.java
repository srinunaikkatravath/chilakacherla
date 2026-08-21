package com.chilakacherla.research.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "duplicate_groups")
public class DuplicateGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String groupName;
    private Long primaryRecordId;
    private Long duplicateRecordId;
    private Integer similarityScore; // 0-100
    private String status; // POSSIBLE_MATCH, CONFIRMED, SEPARATED
    private LocalDate matchedAt;

    public DuplicateGroup() {}

    public DuplicateGroup(String groupName, Long primaryRecordId, Long duplicateRecordId, Integer similarityScore) {
        this.groupName = groupName;
        this.primaryRecordId = primaryRecordId;
        this.duplicateRecordId = duplicateRecordId;
        this.similarityScore = similarityScore;
        this.status = "POSSIBLE_MATCH";
        this.matchedAt = LocalDate.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getGroupName() { return groupName; }
    public void setGroupName(String groupName) { this.groupName = groupName; }

    public Long getPrimaryRecordId() { return primaryRecordId; }
    public void setPrimaryRecordId(Long primaryRecordId) { this.primaryRecordId = primaryRecordId; }

    public Long getDuplicateRecordId() { return duplicateRecordId; }
    public void setDuplicateRecordId(Long duplicateRecordId) { this.duplicateRecordId = duplicateRecordId; }

    public Integer getSimilarityScore() { return similarityScore; }
    public void setSimilarityScore(Integer similarityScore) { this.similarityScore = similarityScore; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getMatchedAt() { return matchedAt; }
    public void setMatchedAt(LocalDate matchedAt) { this.matchedAt = matchedAt; }
}
