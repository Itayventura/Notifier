package com.Itayventura.Notifier.business.service;

import com.Itayventura.Notifier.data.entity.Team;
import com.Itayventura.Notifier.data.repository.TeamRepository;
import com.Itayventura.Notifier.payroll.TeamNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }

    public Team addTeam(Team team){
        return this.teamRepository.save(team);
    }

    public List<Team> getTeams(){
        Iterable<Team> teams = this.teamRepository.findAll();
        List<Team> teamList = new ArrayList<>();
        teams.iterator().forEachRemaining(teamList::add);
        return teamList;
    }

    public Team getTeamById(int id) {
        return this.teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException(id));

    }
}
