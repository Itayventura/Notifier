package com.Itayventura.Notifier.controller;

import com.Itayventura.Notifier.business.service.TeamService;
import com.Itayventura.Notifier.data.entity.Team;
import com.Itayventura.Notifier.payroll.TeamModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
@RequestMapping("/teams")
public class TeamController {
    private final TeamService teamService;
    private final TeamModelAssembler assembler;

    @Autowired
    public TeamController(TeamService teamService, TeamModelAssembler assembler){
        this.teamService = teamService;
        this.assembler = assembler;
    }

    @PostMapping("/new")
    public ResponseEntity<?> addTeam(@RequestBody Team team, UriComponentsBuilder builder){
        Team newTeam = this.teamService.addTeam(team);
        return newTeam == null ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR):
                                new ResponseEntity<>(this.assembler.toModel(team), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Team>>> getAllTeams(){
        List<Team> teams = this.teamService.getTeams();
        List<EntityModel<Team>> entityModels = teams.stream().map(this.assembler::toModel).collect(Collectors.toList());
        return ResponseEntity.ok(CollectionModel.of(entityModels,
                linkTo(methodOn(TeamController.class).getAllTeams()).withSelfRel()));

    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Team>> getTeamById(@PathVariable int id){
        Team team = this.teamService.getTeamById(id);
        return team == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND):
                            new ResponseEntity<>(this.assembler.toModel(team), HttpStatus.OK);
    }

}
