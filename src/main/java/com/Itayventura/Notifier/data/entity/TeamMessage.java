package com.Itayventura.Notifier.data.entity;

import javax.persistence.*;


@Entity
@Table(name = "team_message")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class TeamMessage extends Message {

    public TeamMessage(long messageId, String content, long senderId, long teamId){
        super(messageId, content, senderId);
        this.teamId = teamId;
    }

    public TeamMessage(){
        super();
    }

    @Column(name = "team_id")
    private long teamId;


    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }
}
