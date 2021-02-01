package com.Itayventura.Notifier.controller;

import com.Itayventura.Notifier.business.service.EmployeeService;
import com.Itayventura.Notifier.data.entity.Employee;
import com.Itayventura.Notifier.payroll.EmployeeModelAssembler;
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
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeModelAssembler assembler;

    @Autowired
    public EmployeeController(EmployeeService employeeService, EmployeeModelAssembler assembler){
        this.employeeService = employeeService;
        this.assembler = assembler;
    }

    @GetMapping()
    public ResponseEntity<CollectionModel<EntityModel<Employee>>> getAllEmployees(){
        List<Employee> employees = this.employeeService.getEmployees();
        List<EntityModel<Employee>> entityModels = employees.stream().map(this.assembler::toModel).collect(Collectors.toList());
        return ResponseEntity.ok(CollectionModel.of(entityModels,
                linkTo(methodOn(EmployeeController.class).getAllEmployees()).withSelfRel()));
    }



    @PostMapping("/new")
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee, UriComponentsBuilder builder){
        Employee newEmployee = this.employeeService.addEmployee(employee);
        return newEmployee == null? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR):
                new ResponseEntity<>(this.assembler.toModel(newEmployee), HttpStatus.CREATED);
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
        Employee newEmployee = this.employeeService.updateEmployee(employee);
        return newEmployee == null ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR) :
                new ResponseEntity<>(this.assembler.toModel(newEmployee), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteEmployee(@RequestBody Employee employee){
        this.employeeService.deleteEmployee(employee);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable int id) {
        Employee employee = this.employeeService.getEmployeeById(id);
        return employee == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND):
                new ResponseEntity<>(this.assembler.toModel(employee), HttpStatus.OK);
    }

    @GetMapping(value = "/team/{id}")
    public ResponseEntity<CollectionModel<EntityModel<Employee>>> getTeamEmployeesByTeamId(@PathVariable int id){
        List<Employee> employees = this.employeeService.getTeamEmployees(id);
        List<EntityModel<Employee>> entityModels = employees.stream().map(this.assembler::toModel).collect(Collectors.toList());
        return ResponseEntity.ok(CollectionModel.of(entityModels,
                linkTo(methodOn(EmployeeController.class).getTeamEmployeesByTeamId(id)).withSelfRel()));
    }

}
