package com.Itayventura.Notifier.data.entity;

import lombok.Data;

import javax.persistence.*;

@Data
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

}
