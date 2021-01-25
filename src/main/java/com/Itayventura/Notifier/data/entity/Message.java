package com.Itayventura.Notifier.data.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Message {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int messageId;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private Employee sender;

    @Column(name = "timestamp")
    private LocalDateTime localDateTime;

    public Message(int messageId, String content, Employee sender) {
        this.content = content;
        this.messageId = messageId;
        this.sender = sender;
        this.localDateTime = LocalDateTime.now();
    }

    public Message() {
        this.localDateTime = LocalDateTime.now();
    }

    abstract String getType();

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
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
