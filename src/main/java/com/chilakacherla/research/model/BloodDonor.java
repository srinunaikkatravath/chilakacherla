package com.chilakacherla.research.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BloodDonor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String bloodGroup; // A+, B+, O+, AB+, O-, A-, B-, AB-
    private String phone;
    private Integer age;
    private String locality; // Habitation / Locality
    private String lastDonatedMonth;
    private boolean available = true;

    public BloodDonor() {}

    public BloodDonor(String name, String bloodGroup, String phone, Integer age, String locality) {
        this.name = name;
        this.bloodGroup = bloodGroup;
        this.phone = phone;
        this.age = age;
        this.locality = locality;
        this.lastDonatedMonth = "Eligible Now";
        this.available = true;
    }

    public BloodDonor(String name, String bloodGroup, String phone, String locality, String lastDonatedMonth, boolean available) {
        this.name = name;
        this.bloodGroup = bloodGroup;
        this.phone = phone;
        this.locality = locality;
        this.lastDonatedMonth = lastDonatedMonth;
        this.available = available;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDonorName() { return name; }
    public void setDonorName(String name) { this.name = name; }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getLocality() { return locality; }
    public void setLocality(String locality) { this.locality = locality; }
    public String getHabitation() { return locality; }
    public void setHabitation(String locality) { this.locality = locality; }

    public String getLastDonatedMonth() { return lastDonatedMonth; }
    public void setLastDonatedMonth(String lastDonatedMonth) { this.lastDonatedMonth = lastDonatedMonth; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}
