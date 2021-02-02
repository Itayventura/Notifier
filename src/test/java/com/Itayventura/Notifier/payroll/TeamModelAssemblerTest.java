package com.Itayventura.Notifier.payroll;

import com.Itayventura.Notifier.data.entity.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TeamModelAssemblerTest {

    private TeamModelAssembler assembler;
    private Team team;

    @BeforeEach
    public void setUp() {
        team = new Team(0, "r", "r");
        assembler = new TeamModelAssembler();
    }

    @Test
    public void toModel() {
        EntityModel<Team> entityModel = assembler.toModel(team);
        Team newTeam = entityModel.getContent();
        assertEquals(team, newTeam);

        Links links = entityModel.getLinks();

        Optional<Link> opt = links.getLink("self");
        assertTrue(opt.isPresent());

        Link self = opt.get();
        assertEquals("/teams/"+team.getTeamId(), self.getHref());

        opt = links.getLink("teams");
        assertTrue(opt.isPresent());

        Link all = opt.get();
        assertEquals("/teams", all.getHref());
    }
}