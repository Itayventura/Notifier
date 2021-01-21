package com.Itayventura.Notifier.data.entity;

import javax.persistence.*;

@MappedSuperclass
public class Message {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long messageId;

    @Column(name = "content")
    private String content;

    @Column(name = "sender_id")
    private long senderId;

    public Message(long messageId, String content, long senderId) {
        this.content = content;
        this.messageId = messageId;
        this.senderId = senderId;
    }

    public Message(){

    }


    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId(long senderId) {
        this.senderId = senderId;
    }
}
