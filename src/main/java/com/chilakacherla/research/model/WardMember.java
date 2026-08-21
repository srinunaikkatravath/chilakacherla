package com.chilakacherla.research.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ward_members")
public class WardMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer wardNo;
    private String memberName;
    private String role;
    private String habitation;
    private String phone;

    public WardMember() {}

    public WardMember(Integer wardNo, String memberName, String role, String habitation, String phone) {
        this.wardNo = wardNo;
        this.memberName = memberName;
        this.role = role;
        this.habitation = habitation;
        this.phone = phone;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getWardNo() { return wardNo; }
    public void setWardNo(Integer wardNo) { this.wardNo = wardNo; }

    public String getMemberName() { return memberName; }
    public void setMemberName(String memberName) { this.memberName = memberName; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getHabitation() { return habitation; }
    public void setHabitation(String habitation) { this.habitation = habitation; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
