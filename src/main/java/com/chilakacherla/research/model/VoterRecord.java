package com.chilakacherla.research.model;

import jakarta.persistence.*;

@Entity
@Table(name = "voter_records")
public class VoterRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer wardNo;
    private String habitation;
    private Integer pollingStationNo;
    private String pollingStationName;
    private Integer totalVoters;
    private Integer maleVoters;
    private Integer femaleVoters;
    private String bloName;
    private String bloPhone;

    public VoterRecord() {}

    public VoterRecord(Integer wardNo, String habitation, Integer pollingStationNo, String pollingStationName, Integer totalVoters, Integer maleVoters, Integer femaleVoters, String bloName, String bloPhone) {
        this.wardNo = wardNo;
        this.habitation = habitation;
        this.pollingStationNo = pollingStationNo;
        this.pollingStationName = pollingStationName;
        this.totalVoters = totalVoters;
        this.maleVoters = maleVoters;
        this.femaleVoters = femaleVoters;
        this.bloName = bloName;
        this.bloPhone = bloPhone;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getWardNo() { return wardNo; }
    public void setWardNo(Integer wardNo) { this.wardNo = wardNo; }

    public String getHabitation() { return habitation; }
    public void setHabitation(String habitation) { this.habitation = habitation; }

    public Integer getPollingStationNo() { return pollingStationNo; }
    public void setPollingStationNo(Integer pollingStationNo) { this.pollingStationNo = pollingStationNo; }

    public String getPollingStationName() { return pollingStationName; }
    public void setPollingStationName(String pollingStationName) { this.pollingStationName = pollingStationName; }

    public Integer getTotalVoters() { return totalVoters; }
    public void setTotalVoters(Integer totalVoters) { this.totalVoters = totalVoters; }

    public Integer getMaleVoters() { return maleVoters; }
    public void setMaleVoters(Integer maleVoters) { this.maleVoters = maleVoters; }

    public Integer getFemaleVoters() { return femaleVoters; }
    public void setFemaleVoters(Integer femaleVoters) { this.femaleVoters = femaleVoters; }

    public String getBloName() { return bloName; }
    public void setBloName(String bloName) { this.bloName = bloName; }

    public String getBloPhone() { return bloPhone; }
    public void setBloPhone(String bloPhone) { this.bloPhone = bloPhone; }
}
