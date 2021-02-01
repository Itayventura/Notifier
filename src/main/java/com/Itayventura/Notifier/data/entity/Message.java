package com.Itayventura.Notifier.data.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
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

    public abstract String getType();


}
