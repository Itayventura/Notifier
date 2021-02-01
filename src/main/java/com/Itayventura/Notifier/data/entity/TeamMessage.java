package com.Itayventura.Notifier.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "team_message")
public class TeamMessage extends Message {

    @JoinColumn(name = "team_id")
    @ManyToOne
    @Getter
    @Setter
    private Team team;

    public TeamMessage(int messageId, String content, Employee sender, Team team){
        super(messageId, content, sender);
        this.team = team;
    }

    public TeamMessage(){
        super();
    }

    @Override
    public String getType(){
        return "Team";
    }

    @Override
    public boolean equals(Object object){
        if(object instanceof TeamMessage){
            TeamMessage teamMessage = (TeamMessage) object;
            return super.equals(teamMessage) && this.team.equals(teamMessage.getTeam());

        }
        return false;
    }

}
