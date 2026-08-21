package com.chilakacherla.research.model;

import jakarta.persistence.*;

@Entity
@Table(name = "community_blood_donors")
public class BloodDonor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String donorName;
    private String bloodGroup; // A+, B+, O+, AB+, etc.
    private String phone;
    private Integer age;
    private String locality;
    private Boolean available = true;

    public BloodDonor() {}

    public BloodDonor(String donorName, String bloodGroup, String phone, Integer age, String locality) {
        this.donorName = donorName;
        this.bloodGroup = bloodGroup;
        this.phone = phone;
        this.age = age;
        this.locality = locality;
        this.available = true;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDonorName() { return donorName; }
    public void setDonorName(String donorName) { this.donorName = donorName; }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getLocality() { return locality; }
    public void setLocality(String locality) { this.locality = locality; }

    public Boolean getAvailable() { return available; }
    public void setAvailable(Boolean available) { this.available = available; }
}
