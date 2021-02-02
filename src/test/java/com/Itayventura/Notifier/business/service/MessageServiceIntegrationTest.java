package com.Itayventura.Notifier.business.service;

import com.Itayventura.Notifier.data.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MessageServiceIntegrationTest {

    @Autowired
    private MessageService messageService;

    private static EmployeeMessage employeeMessage;
    private static EmployeeMessage anotherEmployeeMessage;
    private static TeamMessage teamMessage;
    private static TeamMessage anotherTeamMessage;
    private static Employee employee;
    private static Employee sender;
    private static Team team;

    @BeforeAll
    public static void setUpClass(){
        team =  new Team(1, "sw1", "R&D");
        sender = new Employee(2, "Mani", "mani", "c@c.com", "Team leader", team);
        teamMessage = new TeamMessage(1, "content", sender, team);
        anotherTeamMessage = new TeamMessage(2, "anotherContent", sender, team);
        employee = new Employee(1, "Itay", "Ventura", "a@a.com", "software developer", new Team(1, "sw1", "R&D"));
        employeeMessage = new EmployeeMessage(1, "content", sender, employee);
        anotherEmployeeMessage = new EmployeeMessage(2, "anotherContent", sender, employee);

    }

    @BeforeEach
    public void setUp() {
        messageService.addTeamMessage(anotherTeamMessage);
        messageService.addEmployeeMessages(anotherEmployeeMessage);
    }

    @Test
    public void addEmployeeMessages() {
        EmployeeMessage newMessage = this.messageService.addEmployeeMessages(employeeMessage);
        assertNotNull(newMessage);
        assertEquals(employeeMessage, newMessage);
    }

    @Test
    public void addTeamMessage() {
        TeamMessage newMessage = this.messageService.addTeamMessage(teamMessage);
        assertNotNull(newMessage);
        assertEquals(newMessage, teamMessage);
    }

    @Test
    public void getAllMessages() {
        List<Message> messageList = this.messageService.getAllMessages();
        assertFalse(messageList.isEmpty());
    }

    @Test
    public void getAllMessagesByEmployee() {
        List<Message> messages = this.messageService.getAllMessagesByEmployee(employee);
        assertFalse(messages.isEmpty());
        messages.forEach(message -> {
            if (message.getType().equals("Team")) {
                TeamMessage teamMessage = (TeamMessage) message;
                assertEquals(team, teamMessage.getTeam());
            } else if (message.getType().equals("Employee")) {
                EmployeeMessage employeeMessage = (EmployeeMessage) message;
                assertEquals(employee, employeeMessage.getEmployee());
            }
        });
    }

    @Test
    public void getAllTeamMessagesByTeam() {
        List<TeamMessage> teamMessages = this.messageService.getAllTeamMessagesByTeam(team);
        assertFalse(teamMessages.isEmpty());
        teamMessages.forEach(newTeamMessage -> assertEquals(team, newTeamMessage.getTeam()));
    }
}