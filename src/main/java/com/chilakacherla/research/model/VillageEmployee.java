package com.chilakacherla.research.model;

import jakarta.persistence.*;

@Entity
@Table(name = "village_employees")
public class VillageEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String designation;
    private String department;
    private String employeeType; // "Government", "PSU", "Private / IT", "Banking"
    private String workLocation;
    private String habitation;
    private String phone;

    public VillageEmployee() {}

    public VillageEmployee(String name, String designation, String department, String employeeType, String workLocation, String habitation, String phone) {
        this.name = name;
        this.designation = designation;
        this.department = department;
        this.employeeType = employeeType;
        this.workLocation = workLocation;
        this.habitation = habitation;
        this.phone = phone;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getEmployeeType() { return employeeType; }
    public void setEmployeeType(String employeeType) { this.employeeType = employeeType; }

    public String getWorkLocation() { return workLocation; }
    public void setWorkLocation(String workLocation) { this.workLocation = workLocation; }

    public String getHabitation() { return habitation; }
    public void setHabitation(String habitation) { this.habitation = habitation; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
