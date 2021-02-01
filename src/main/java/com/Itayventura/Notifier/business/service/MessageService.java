package com.Itayventura.Notifier.business.service;

import com.Itayventura.Notifier.business.domain.Merger;
import com.Itayventura.Notifier.data.entity.*;
import com.Itayventura.Notifier.data.repository.EmployeeMessageRepository;
import com.Itayventura.Notifier.data.repository.TeamMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public EmployeeMessage addEmployeeMessages(EmployeeMessage message){
        return this.employeeMessageRepository.save(message);
    }


    public TeamMessage addTeamMessage(TeamMessage message){
        return this.teamMessageRepository.save(message);
    }

    public List<Message> getAllMessages(){
        Iterable<? extends Message> teamMessages = this.teamMessageRepository.findAll();
        Iterable<? extends Message> employeeMessages = this.employeeMessageRepository.findAll();
        return Merger.mergeIterators(teamMessages.iterator(), employeeMessages.iterator());
    }

    public List<Message> getAllMessagesByEmployee(Employee employee){
        Iterable<? extends Message> teamMessages = this.teamMessageRepository.findAllByTeam(employee.getTeam());
        Iterable<? extends Message> employeeMessages = this.employeeMessageRepository.findAllByEmployee(employee);
        return Merger.mergeIterators(teamMessages.iterator(), employeeMessages.iterator());
    }

    public List<TeamMessage> getAllTeamMessagesByTeam(Team team){
        Iterable<TeamMessage> teamMessages = this.teamMessageRepository.findAllByTeam(team);
        List<TeamMessage> messages = new ArrayList<>();
        teamMessages.iterator().forEachRemaining(messages::add);
        return messages;
    }



}
