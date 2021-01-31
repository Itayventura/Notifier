package com.Itayventura.Notifier.payroll;

import com.Itayventura.Notifier.controller.TeamController;
import com.Itayventura.Notifier.data.entity.Team;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TeamModelAssembler implements RepresentationModelAssembler<Team, EntityModel<Team>> {
    @Override
    public EntityModel<Team> toModel(Team team) {
        return EntityModel.of(team,
                linkTo(methodOn(TeamController.class).getTeamById(team.getTeamId())).withSelfRel(),
                linkTo(methodOn(TeamController.class).getAllTeams()).withRel("team"));
    }

}

