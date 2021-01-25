package com.Itayventura.Notifier.controller;

import com.Itayventura.Notifier.business.service.EmployeeService;
import com.Itayventura.Notifier.business.service.TeamEmployeesService;
import com.Itayventura.Notifier.data.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

//todo handle (team does not exist) java.util.NoSuchElementException:
@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final TeamEmployeesService teamEmployeesService;


    @Autowired
    public EmployeeController(EmployeeService employeeService, TeamEmployeesService teamEmployeesService){
        this.employeeService = employeeService;
        this.teamEmployeesService = teamEmployeesService;
    }

    @GetMapping
    public String getEmployees(Model model){
            Iterable<Employee> employees = this.employeeService.getEmployees();
            model.addAttribute("employees", employees);
            return "employees";
    }

    @GetMapping(value = "/team")
    public String getTeamEmployees(@RequestParam(value="team")String teamName, Model model){
        Iterable<Employee> teamEmployees = this.teamEmployeesService.getTeamEmployees(teamName);
        model.addAttribute("employees", teamEmployees);
        return "employees";
    }

    @PostMapping("/new")
    public ResponseEntity<Void> addEmployee(@RequestBody Employee employee, UriComponentsBuilder builder){
        this.employeeService.addEmployee(employee);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/employee/new/{id}").buildAndExpand(employee.getEmployeeId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
        this.employeeService.updateEmployee(employee);
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteEmployee(@RequestBody Employee employee){
        this.employeeService.deleteEmployee(employee);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
