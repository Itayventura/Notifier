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

    @PostMapping()
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee, UriComponentsBuilder builder){
        Employee newEmployee = this.employeeService.addEmployee(employee);
        if (newEmployee == null){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        EntityModel<Employee> entityModel = this.assembler.toModel(this.employeeService.addEmployee(employee));
        return new ResponseEntity<>(entityModel, HttpStatus.CREATED);
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee){
        EntityModel<Employee> entityModel = this.assembler.toModel(this.employeeService.updateEmployee(employee));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);

    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteEmployee(@RequestBody Employee employee){
        this.employeeService.deleteEmployee(employee);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


    //todo as same as above (all) return response entity
    @GetMapping(value = "/team")
    public String getTeamEmployees(@RequestParam(value="team")String teamName, Model model){
        Iterable<Employee> teamEmployees = this.teamEmployeesService.getTeamEmployees(teamName);
        model.addAttribute("employees", teamEmployees);
        return "employees";
    }

    public void Print(){
        System.out.println("hello");
    }

}
