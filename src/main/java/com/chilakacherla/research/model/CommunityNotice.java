package com.chilakacherla.research.model;

import jakarta.persistence.*;

@Entity
@Table(name = "community_notices")
public class CommunityNotice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String category; // Panchayat, Festival, Emergency, School, Agriculture
    private String priority; // URGENT, HIGH, NORMAL
    private String noticeDate;
    private String postedBy;

    @Column(columnDefinition = "TEXT")
    private String details;

    public CommunityNotice() {}

    public CommunityNotice(String title, String category, String priority, String noticeDate, String postedBy, String details) {
        this.title = title;
        this.category = category;
        this.priority = priority;
        this.noticeDate = noticeDate;
        this.postedBy = postedBy;
        this.details = details;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public String getNoticeDate() { return noticeDate; }
    public void setNoticeDate(String noticeDate) { this.noticeDate = noticeDate; }

    public String getPostedBy() { return postedBy; }
    public void setPostedBy(String postedBy) { this.postedBy = postedBy; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
}
