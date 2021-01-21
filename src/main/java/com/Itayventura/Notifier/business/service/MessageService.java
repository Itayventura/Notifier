package com.Itayventura.Notifier.business.service;

import com.Itayventura.Notifier.data.entity.Message;
import com.Itayventura.Notifier.data.entity.TeamMessage;
import com.Itayventura.Notifier.data.repository.MessageRepository;
import com.Itayventura.Notifier.data.repository.TeamMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final TeamMessageRepository teamMessageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, TeamMessageRepository teamMessageRepository){
        this.messageRepository = messageRepository;
        this.teamMessageRepository = teamMessageRepository;
    }

    public Iterable<TeamMessage> getTeamMessages(){
        return this.teamMessageRepository.findAll();
        }
}
