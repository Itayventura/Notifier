package com.Itayventura.Notifier.controller;

import com.Itayventura.Notifier.business.service.EmployeeService;
import com.Itayventura.Notifier.business.service.TeamEmployeesService;
import com.Itayventura.Notifier.data.entity.Employee;
import com.Itayventura.Notifier.payroll.EmployeeModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

//todo handle (team does not exist) java.util.NoSuchElementException:
@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeModelAssembler assembler;
    private final TeamEmployeesService teamEmployeesService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, TeamEmployeesService teamEmployeesService, EmployeeModelAssembler assembler){
        this.employeeService = employeeService;
        this.teamEmployeesService = teamEmployeesService;
        this.assembler = assembler;
    }

    @GetMapping()
    public ResponseEntity<CollectionModel<EntityModel<Employee>>> all(){
        List<Employee> employees = this.employeeService.getEmployees();
        List<EntityModel<Employee>> entityModels = employees.stream().map(this.assembler::toModel).collect(Collectors.toList());
        return ResponseEntity.ok(CollectionModel.of(entityModels,
                linkTo(methodOn(EmployeeController.class).all()).withSelfRel()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Employee>> one(@PathVariable int id) {
        Employee employee = this.employeeService.getEmployeeById(id);

        EntityModel<Employee> entityModel = this.assembler.toModel(employee);

        return new ResponseEntity<>(entityModel, HttpStatus.OK);
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

    //todo handle somehow updated or not ?
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
