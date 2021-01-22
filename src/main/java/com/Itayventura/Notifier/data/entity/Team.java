package com.Itayventura.Notifier.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "team")
public class Team {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teamId;

    @Column(name = "name")
    private String name;

    @Column(name = "department")
    private String department;

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
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

    @Override
    public String toString(){
        return this.getTeamId() + " " + this.getName() + " " + this.getDepartment();
    }
}
