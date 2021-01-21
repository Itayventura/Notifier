package com.Itayventura.Notifier.controller;

import com.Itayventura.Notifier.business.service.MessageService;
import com.Itayventura.Notifier.business.service.TeamEmployeesService;
import com.Itayventura.Notifier.data.entity.Message;
import com.Itayventura.Notifier.data.entity.TeamMessage;
import com.Itayventura.Notifier.data.repository.MessageRepository;
import com.Itayventura.Notifier.data.repository.TeamMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;
    private final TeamEmployeesService teamEmployeesService;

    @Autowired
    public MessageController(TeamEmployeesService teamEmployeesService,
                             MessageService messageService ,
                             MessageRepository messageRepository,
                             TeamMessageRepository teamMessageRepository){
        this.teamEmployeesService = teamEmployeesService;
        this.messageService = messageService;
    }

    @GetMapping
    public String getTeamMessages(Model model){
        Iterable<TeamMessage> messages = this.messageService.getTeamMessages();
        model.addAttribute("messages", messages);
        return "messages";
    }

    //todo send message
    @PostMapping(value = "/team")
    public void sendTeamMessage(@RequestParam(value="firstName")String firstName,
                            @RequestParam(value="lastName")String lastName,
                            @RequestParam(value="teamName")String teamName,
                            Model model){

    }

}

