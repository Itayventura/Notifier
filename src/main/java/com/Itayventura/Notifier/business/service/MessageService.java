package com.Itayventura.Notifier.business.service;

import com.Itayventura.Notifier.business.domain.Merger;
import com.Itayventura.Notifier.data.entity.EmployeeMessage;
import com.Itayventura.Notifier.data.entity.Message;
import com.Itayventura.Notifier.data.entity.TeamMessage;
import com.Itayventura.Notifier.data.repository.EmployeeMessageRepository;
import com.Itayventura.Notifier.data.repository.TeamMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final TeamMessageRepository teamMessageRepository;
    private final EmployeeMessageRepository employeeMessageRepository;


    @Autowired
    public MessageService(TeamMessageRepository teamMessageRepository,
                          EmployeeMessageRepository employeeMessageRepository){
        this.teamMessageRepository = teamMessageRepository;
        this.employeeMessageRepository = employeeMessageRepository;
    }

    public Iterable<TeamMessage> getTeamMessages(){
        return this.teamMessageRepository.findAll();
    }

    public void addTeamMessage(TeamMessage message){
        this.teamMessageRepository.save(message);
    }

    public List<Message> getMessages(){
        Iterable<? extends Message> teamMessages = getTeamMessages();
        Iterable<? extends Message> employeeMessages = getEmployeeMessages();
        return Merger.mergeIterators(teamMessages.iterator(), employeeMessages.iterator());
    }

    public Iterable<EmployeeMessage> getEmployeeMessages(){
        return this.employeeMessageRepository.findAll();
    }

    public void addEmployeeMessages(EmployeeMessage message){
        this.employeeMessageRepository.save(message);
    }

}
