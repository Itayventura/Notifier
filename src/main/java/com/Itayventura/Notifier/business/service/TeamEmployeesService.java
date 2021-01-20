package com.Itayventura.Notifier.business.service;

import com.Itayventura.Notifier.data.entity.Employee;
import com.Itayventura.Notifier.data.entity.Team;
import com.Itayventura.Notifier.data.repository.EmployeeRepository;
import com.Itayventura.Notifier.data.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamEmployeesService {
    private final TeamRepository teamRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public TeamEmployeesService(TeamRepository teamRepository, EmployeeRepository employeeRepository){
        this.teamRepository = teamRepository;
        this.employeeRepository = employeeRepository;
    }

    public Iterable<Employee> getTeamEmployees(String teamName){
        Team team = this.teamRepository.findOneByName(teamName).get();
        Iterable<Employee> teamEmployees = this.employeeRepository.findAllByTeamId(team.getTeamId());
        return teamEmployees;
    }
}
