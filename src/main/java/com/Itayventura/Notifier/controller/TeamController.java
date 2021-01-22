package com.Itayventura.Notifier.controller;

import com.Itayventura.Notifier.business.service.TeamService;
import com.Itayventura.Notifier.data.entity.Team;
import com.Itayventura.Notifier.data.entity.TeamMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("/team")
public class TeamController {
    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService){
        this.teamService = teamService;
    }

    @PostMapping("/new")
    public ResponseEntity<Void> addTeam(@RequestBody Team team, UriComponentsBuilder builder){
        this.teamService.addTeam(team);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/new/{id}").buildAndExpand(team.getTeamId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @GetMapping
    public String getTeams(Model model){
        Iterable<Team> teams = this.teamService.getTeams();
        model.addAttribute("team", teams);
        return "team";
    }
}
