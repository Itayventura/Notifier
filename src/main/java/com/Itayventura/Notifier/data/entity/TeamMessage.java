package com.Itayventura.Notifier.data.entity;

import javax.persistence.*;


@Entity
@Table(name = "team_message")
public class TeamMessage extends Message {

    @JoinColumn(name = "team_id")
    @ManyToOne
    private Team team;

    public TeamMessage(int messageId, String content, Employee sender, Team team){
        super(messageId, content, sender);
        this.team = team;
    }

    public TeamMessage(String content, Employee sender, Team team){
        super(content, sender);
        this.team = team;
    }

    public TeamMessage(){
        super();
    }


    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
