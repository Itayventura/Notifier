package com.Itayventura.Notifier.data.repository;

import com.Itayventura.Notifier.data.entity.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
class TeamRepositoryTest {


    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private TeamRepository teamRepository;

    private Team team;

    @BeforeEach
    public void setTeam(){
        team = new Team();
        team.setTeamName("sw11");
        team.setDepartment("R&D");
    }


    @Test
    void findOneByName() {
        testEntityManager.persist(team);
        Optional<Team> opt = teamRepository.findOneByName(team.getName());
        assertTrue(!opt.isEmpty());
        Team foundTeam = opt.get();
        assertEquals(team.getName(), foundTeam.getName());
        assertEquals(team.getDepartment(), foundTeam.getDepartment());


    }
}