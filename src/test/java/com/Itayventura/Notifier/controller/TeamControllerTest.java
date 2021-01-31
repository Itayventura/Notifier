package com.Itayventura.Notifier.controller;

import com.Itayventura.Notifier.business.service.TeamService;
import com.Itayventura.Notifier.data.entity.Team;
import com.Itayventura.Notifier.payroll.TeamModelAssembler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TeamController.class)
@AutoConfigureMockMvc
public class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeamService teamService;

    @MockBean
    private TeamModelAssembler assembler;

    @InjectMocks
    private TeamController teamController;

    private Team aMockTeam;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Before
    public void setUpTeam(){
        aMockTeam = new Team(0, "sw3", "R&D");
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testAddTeam() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        when(teamService.addTeam(any(Team.class))).thenReturn(aMockTeam);
        mockMvc.perform(MockMvcRequestBuilders.post("/teams/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(aMockTeam))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
        verify(teamService, times(1)).addTeam(any(Team.class));
    }

    @Test
    public void testAddIllegalTeam() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        when(teamService.addTeam(any(Team.class))).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.post("/teams/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(aMockTeam))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
        verify(teamService, times(1)).addTeam(any(Team.class));


    }

    @Test
    public void testGetAllTeams() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/teams")).andExpect(status().isOk());
        verify(teamService, times(1)).getTeams();
    }

    @Test
    public void testGetTeamById() throws Exception {
        when(teamService.getTeamById(any(Integer.class))).thenReturn(aMockTeam);
        mockMvc.perform(MockMvcRequestBuilders.get("/teams/1"))
                .andExpect(status().isOk());
        verify(teamService, times(1)).getTeamById(any(Integer.class));
    }

    @Test
    public void testGetIllegalTeamById() throws Exception {
        when(teamService.getTeamById(any(Integer.class))).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/teams/1"))
                .andExpect(status().is4xxClientError());
        verify(teamService, times(1)).getTeamById(any(Integer.class));
    }

}