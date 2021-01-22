package com.Itayventura.Notifier.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "team")
public class Team {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer teamId;

    @Column(name = "name")
    private String name;

    @Column(name = "department")
    private String department;

    public Team(){

    }

    public Team(Integer teamId, String name, String department) {
        this.teamId = teamId;
        this.name = name;
        this.department = department;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setTeamName(String teamName) {
        this.name = teamName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
