package com.Itayventura.Notifier.controller;

import com.Itayventura.Notifier.business.service.MessageService;
import com.Itayventura.Notifier.data.entity.EmployeeMessage;
import com.Itayventura.Notifier.data.entity.Message;
import com.Itayventura.Notifier.data.entity.TeamMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Controller
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService){
        this.messageService = messageService;
    }

    @GetMapping("/team")
    public String getTeamMessages(Model model){
        Iterable<TeamMessage> messages = this.messageService.getTeamMessages();
        model.addAttribute("messages", messages);
        return "messages";
    }

    @PostMapping("/team/new")
    public ResponseEntity<Void> addTeamMessage(@RequestBody TeamMessage message, UriComponentsBuilder builder) {
        this.messageService.addTeamMessage(message);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("team/new/{id}").buildAndExpand(message.getMessageId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }


    @GetMapping("/employee")
    public String getEmployeeMessages(Model model){
        Iterable<EmployeeMessage> messages = this.messageService.getEmployeeMessages();
        model.addAttribute("messages", messages);
        return "messages";
    }

    @PostMapping("/employee/new")
    public ResponseEntity<Void> addEmployeeMessage(@RequestBody EmployeeMessage message, UriComponentsBuilder builder) {
        this.messageService.addEmployeeMessages(message);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("employee/new/{id}").buildAndExpand(message.getMessageId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @GetMapping
    public String getMessages(Model model){
        List<Message> messages = this.messageService.getMessages();
        model.addAttribute("messages", messages);
        return "messages";
    }


}

