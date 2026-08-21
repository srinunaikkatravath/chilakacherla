package com.chilakacherla.research.model;

import jakarta.persistence.*;

@Entity
@Table(name = "educated_candidates")
public class EducatedCandidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String degree;
    private String specialization;
    private Integer passoutYear;
    private String skills;
    private String status; // "LOOKING_FOR_JOB", "PREPARING_COMPETITIVE", "EMPLOYED"
    private String habitation;
    private String phone;

    public EducatedCandidate() {}

    public EducatedCandidate(String name, String degree, String specialization, Integer passoutYear, String skills, String status, String habitation, String phone) {
        this.name = name;
        this.degree = degree;
        this.specialization = specialization;
        this.passoutYear = passoutYear;
        this.skills = skills;
        this.status = status;
        this.habitation = habitation;
        this.phone = phone;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDegree() { return degree; }
    public void setDegree(String degree) { this.degree = degree; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public Integer getPassoutYear() { return passoutYear; }
    public void setPassoutYear(Integer passoutYear) { this.passoutYear = passoutYear; }

    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getHabitation() { return habitation; }
    public void setHabitation(String habitation) { this.habitation = habitation; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
