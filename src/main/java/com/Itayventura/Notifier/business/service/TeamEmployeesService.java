package com.Itayventura.Notifier.business.service;

import com.Itayventura.Notifier.data.entity.Employee;
import com.Itayventura.Notifier.data.entity.Team;
import com.Itayventura.Notifier.data.repository.EmployeeRepository;
import com.Itayventura.Notifier.data.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamEmployeesService {
    private final TeamRepository teamRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public TeamEmployeesService(TeamRepository teamRepository, EmployeeRepository employeeRepository){
        this.teamRepository = teamRepository;
        this.employeeRepository = employeeRepository;
    }

    //todo notFound Exception
    public List<Employee> getTeamEmployees(String teamName){
        Team team = this.teamRepository.findOneByName(teamName).get();
        Iterable<Employee> teamEmployees = this.employeeRepository.findAllByTeam(team);
        List<Employee> employeeList = new ArrayList<>();
        teamEmployees.iterator().forEachRemaining(employeeList::add);
        return employeeList;
    }
}
