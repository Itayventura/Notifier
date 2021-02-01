package com.Itayventura.Notifier.data.repository;

import com.Itayventura.Notifier.data.entity.Employee;
import com.Itayventura.Notifier.data.entity.Team;
import com.Itayventura.Notifier.data.entity.TeamMessage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TeamMessageRepositoryTest {


    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private TeamMessageRepository teamMessageRepository;

    private TeamMessage teamMessage;
    private Team team;
    private Employee sender;

    @Before
    public void setUp() {
        team =  new Team(1, "sw1", "R&D");
        sender = new Employee(2, "Mani", "mani", "c@c.com", "Team Leader", team);
        teamMessage = new TeamMessage(0, "content", sender, team);
    }


    @Test
    public void findAllByTeam() {
        testEntityManager.persist(teamMessage);
        Iterable<TeamMessage> employeeMessages = teamMessageRepository.findAllByTeam(team);
        Iterator<TeamMessage> it = employeeMessages.iterator();
        assertTrue(it.hasNext());
        while(it.hasNext()){
            TeamMessage teamMessage = it.next();
            assertEquals(team, teamMessage.getTeam());
        }
    }
}