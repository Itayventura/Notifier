package com.Itayventura.Notifier.data.entity;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Message {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int messageId;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Employee sender;

    public Message(int messageId, String content, Employee sender) {
        this.content = content;
        this.messageId = messageId;
        this.sender = sender;
    }

    public Message(String content, Employee sender) {
        this.content = content;
        this.sender = sender;
    }

    public Message() {

    }


    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Employee getSender() {
        return sender;
    }

    public void setSender(Employee sender) {
        this.sender = sender;
    }
}