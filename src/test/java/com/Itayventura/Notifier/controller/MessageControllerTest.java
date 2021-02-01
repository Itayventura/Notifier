package com.Itayventura.Notifier.controller;

import com.Itayventura.Notifier.business.service.MessageService;
import com.Itayventura.Notifier.data.entity.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MessageController.class)
@AutoConfigureMockMvc
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private MessageService messageService;

    @InjectMocks
    private MessageController messageController;

    private TeamMessage teamMessage;
    private EmployeeMessage employeeMessage;
    private Team team;
    private Employee employee;

    @Before
    public void setUpVariables(){
        team =  new Team(1, "sw1", "R&D");
        Employee sender = new Employee(2, "Mani", "mani", "c@c.com", "Team leader", team);
        teamMessage = new TeamMessage(1, "content", sender, team);
        employee = new Employee(1, "Itay", "Ventura", "a@a.com", "software developer", new Team(1, "sw1", "R&D"));
        employeeMessage = new EmployeeMessage(1, "content", sender, employee);
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @PostConstruct
    public void setUpObjectMapper() {
        mapper.registerModule(new JavaTimeModule());
    }


    @Test
    public void getAllTeamMessagesByTeam() throws Exception {

        List<TeamMessage> teamMessageList = new ArrayList<>();
        teamMessageList.add(teamMessage);
        when(messageService.getAllTeamMessagesByTeam(any(Team.class))).thenReturn(teamMessageList);

        RequestBuilder requestBuilder =  MockMvcRequestBuilders.get("/messages/team/"+team.getTeamId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(team))
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andReturn();
        verify(messageService, times(1)).getAllTeamMessagesByTeam(teamMessage.getTeam());

        List<TeamMessage> teamMessages = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<TeamMessage>>(){});
        assertFalse(teamMessages.isEmpty());
        assertEquals(teamMessage,teamMessages.get(0));

    }

    @Test
    public void addTeamMessage() throws Exception {
        when(messageService.addTeamMessage(any(TeamMessage.class))).thenReturn(teamMessage);
        mockMvc.perform(MockMvcRequestBuilders.post("/messages/team/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(teamMessage))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
        verify(messageService, times(1)).addTeamMessage(any(TeamMessage.class));
    }

    @Test
    public void addIllegalTeamMessage() throws Exception {
        when(messageService.addTeamMessage(any(TeamMessage.class))).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/messages/team/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(teamMessage))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void getAllMessagesByEmployee() throws Exception {
        List<Message> messages = new ArrayList<>();
        messages.add(teamMessage);
        messages.add(employeeMessage);
        when(messageService.getAllMessagesByEmployee(any(Employee.class))).thenReturn(messages);
        RequestBuilder requestBuilder =  MockMvcRequestBuilders.get("/messages/employee/"+employee.getEmployeeId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(employee))
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
        verify(messageService, times(1)).getAllMessagesByEmployee(employee);

    }

    @Test
    public void addEmployeeMessage() throws Exception {
        when(messageService.addEmployeeMessages(any(EmployeeMessage.class))).thenReturn(employeeMessage);
        mockMvc.perform(MockMvcRequestBuilders.post("/messages/employee/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(teamMessage))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
        verify(messageService, times(1)).addEmployeeMessages(any(EmployeeMessage.class));

    }

    @Test
    public void getAllMessages() throws Exception {
        List<Message> messages = new ArrayList<>();
        messages.add(teamMessage);
        messages.add(employeeMessage);
        when(messageService.getAllMessages()).thenReturn(messages);
        mockMvc.perform(MockMvcRequestBuilders.get("/messages"))
                .andExpect(status().isOk());
        verify(messageService, times(1)).getAllMessages();

    }
}