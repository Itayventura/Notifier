package com.Itayventura.Notifier.data.repository;

import com.Itayventura.Notifier.data.entity.Team;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TeamRepositoryTest {


    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private TeamRepository teamRepository;

    private Team team;

    @Before
    public void setTeam(){
        team = new Team(0, "sw11", "R&D");
    }


    @Test
    public void findOneByName() {

        testEntityManager.persist(team);
        Optional<Team> opt = teamRepository.findOneByName(team.getName());
        assertFalse(opt.isEmpty());
        Team foundTeam = opt.get();
        assertEquals(team, foundTeam);

    }
}