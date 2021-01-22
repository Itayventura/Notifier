package com.Itayventura.Notifier.business.service;

import com.Itayventura.Notifier.data.entity.Team;
import com.Itayventura.Notifier.data.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }

    public void addTeam(Team team){
        this.teamRepository.save(team);
    }

    public Iterable<Team> getTeams(){
        return this.teamRepository.findAll();
    }
}
