package com.Itayventura.Notifier.business.service;

import com.Itayventura.Notifier.data.entity.Team;
import com.Itayventura.Notifier.payroll.TeamNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TeamServiceIntegrationTest {

    @Autowired
    private TeamService teamService;

    private Team team;
    private Team anotherTeam;

    @BeforeEach
    public void setUp() {
        team = new Team(0, "sw3", "R&D");
        anotherTeam = new Team(0, "sw4", "R&D");
        teamService.addTeam(anotherTeam);
    }

    @Test
    public void testAddTeam() {
        Team newTeam = teamService.addTeam(team);
        assertNotNull(newTeam);
        assertEquals(team, newTeam);
    }

    @Test
    public void testAddIllegalTeam(){
        Assertions.assertThrows(org.springframework.dao.DataIntegrityViolationException.class, () -> {
            teamService.addTeam(new Team());
        });

    }

    @Test
    public void testGetTeamById(){
        Team newTeam = teamService.getTeamById(anotherTeam.getTeamId());
        assertNotNull(newTeam);
        assertEquals(anotherTeam, newTeam);
    }

    @Test
    public void testGetNotExistingTeamById() {
        Assertions.assertThrows(TeamNotFoundException.class, () -> {
            teamService.getTeamById(Integer.MAX_VALUE);
        });

    }
}