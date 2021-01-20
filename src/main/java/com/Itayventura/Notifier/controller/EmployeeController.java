package com.Itayventura.Notifier.controller;

import com.Itayventura.Notifier.business.service.TeamEmployeesService;
import com.Itayventura.Notifier.data.entity.Employee;
import com.Itayventura.Notifier.data.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//todo handle (team does not exist) java.util.NoSuchElementException:
@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    private final TeamEmployeesService teamEmployeesService;


    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository, TeamEmployeesService teamEmployeesService){
        this.employeeRepository = employeeRepository;
        this.teamEmployeesService = teamEmployeesService;
    }

    @GetMapping
    public String getEmployees(Model model){
            Iterable<Employee> employees = this.employeeRepository.findAll();
            model.addAttribute("employees", employees);
            return "employees";
    }

    @GetMapping(value = "/team")
    public String getTeamEmployees(@RequestParam(value="team")String teamName, Model model){
        Iterable<Employee> teamEmployees = this.teamEmployeesService.getTeamEmployees(teamName);
        model.addAttribute("employees", teamEmployees);
        return "employees";
    }
}
