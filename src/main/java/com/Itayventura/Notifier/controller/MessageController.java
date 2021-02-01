package com.Itayventura.Notifier.controller;

import com.Itayventura.Notifier.business.service.MessageService;
import com.Itayventura.Notifier.data.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService){
        this.messageService = messageService;
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<TeamMessage>> getAllTeamMessagesByTeam(@PathVariable int teamId, @RequestBody Team team){
        List<TeamMessage> messages = this.messageService.getAllTeamMessagesByTeam(team);
        return ResponseEntity.ok(messages);
    }

    @PostMapping("/team/new")
    public ResponseEntity<?> addTeamMessage(@RequestBody TeamMessage message) {
        TeamMessage teamMessage = this.messageService.addTeamMessage(message);
        return teamMessage == null ?
                new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR):
                new ResponseEntity<>(teamMessage, HttpStatus.CREATED);
    }


    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<?> getAllMessagesByEmployee(@PathVariable int employeeId, @RequestBody Employee employee){
        List<Message> messages = this.messageService.getAllMessagesByEmployee(employee);
        return ResponseEntity.ok(messages);
    }

    @PostMapping("/employee/new")
    public ResponseEntity<?> addEmployeeMessage(@RequestBody EmployeeMessage message) {
        EmployeeMessage employeeMessage = this.messageService.addEmployeeMessages(message);
        return employeeMessage == null ?
                new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) :
                new ResponseEntity<>(employeeMessage, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> getAllMessages(){
        List<Message> messages = this.messageService.getAllMessages();
        return ResponseEntity.ok(messages);
    }


}

