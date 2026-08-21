package com.chilakacherla.research.model;

import jakarta.persistence.*;

@Entity
@Table(name = "voter_members")
public class VoterMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String epicNo;
    private String voterName;
    private String relationName;
    private String houseNo;
    private Integer age;
    private String gender;
    private Integer wardNo;
    private String habitation;
    private Integer pollingStationNo;

    public VoterMember() {}

    public VoterMember(String epicNo, String voterName, String relationName, String houseNo, Integer age, String gender, Integer wardNo, String habitation, Integer pollingStationNo) {
        this.epicNo = epicNo;
        this.voterName = voterName;
        this.relationName = relationName;
        this.houseNo = houseNo;
        this.age = age;
        this.gender = gender;
        this.wardNo = wardNo;
        this.habitation = habitation;
        this.pollingStationNo = pollingStationNo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEpicNo() { return epicNo; }
    public void setEpicNo(String epicNo) { this.epicNo = epicNo; }

    public String getVoterName() { return voterName; }
    public void setVoterName(String voterName) { this.voterName = voterName; }

    public String getRelationName() { return relationName; }
    public void setRelationName(String relationName) { this.relationName = relationName; }

    public String getHouseNo() { return houseNo; }
    public void setHouseNo(String houseNo) { this.houseNo = houseNo; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public Integer getWardNo() { return wardNo; }
    public void setWardNo(Integer wardNo) { this.wardNo = wardNo; }

    public String getHabitation() { return habitation; }
    public void setHabitation(String habitation) { this.habitation = habitation; }

    public Integer getPollingStationNo() { return pollingStationNo; }
    public void setPollingStationNo(Integer pollingStationNo) { this.pollingStationNo = pollingStationNo; }
}
