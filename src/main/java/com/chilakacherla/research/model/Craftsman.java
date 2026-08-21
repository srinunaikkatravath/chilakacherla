package com.chilakacherla.research.model;

import jakarta.persistence.*;

@Entity
@Table(name = "community_craftsmen")
public class Craftsman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String trade; // Electrician, Plumber, Mechanic, Driver, Mason, Carpenter, Tailor
    private String phone;
    private String locality;
    private Integer experienceYears;
    private Double rating;

    public Craftsman() {}

    public Craftsman(String name, String trade, String phone, String locality, Integer experienceYears, Double rating) {
        this.name = name;
        this.trade = trade;
        this.phone = phone;
        this.locality = locality;
        this.experienceYears = experienceYears;
        this.rating = rating;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTrade() { return trade; }
    public void setTrade(String trade) { this.trade = trade; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getLocality() { return locality; }
    public void setLocality(String locality) { this.locality = locality; }

    public Integer getExperienceYears() { return experienceYears; }
    public void setExperienceYears(Integer experienceYears) { this.experienceYears = experienceYears; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }
}
