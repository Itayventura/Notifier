package com.Itayventura.Notifier.business.service;

import com.Itayventura.Notifier.data.entity.Employee;
import com.Itayventura.Notifier.data.repository.EmployeeRepository;
import com.Itayventura.Notifier.payroll.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee getEmployeeById(int id){
        return this.employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public Employee getEmployeeByName(String firstName, String lastName){
        return Optional.ofNullable(this.employeeRepository.findByFirstNameAndLastName(firstName, lastName))
                .orElseThrow(() -> new EmployeeNotFoundException(firstName, lastName));
    }

    public List<Employee> getEmployees(){
        Iterable<Employee> employees = this.employeeRepository.findAll();
        List<Employee> employeeList = new ArrayList<>();
        employees.iterator().forEachRemaining(employeeList::add);
        return employeeList;
    }

    public List<Employee> getTeamEmployees(int teamId){
        Iterable<Employee> teamEmployees = this.employeeRepository.findAllByTeam_TeamId(teamId);
        List<Employee> employeeList = new ArrayList<>();
        teamEmployees.iterator().forEachRemaining(employeeList::add);
        return employeeList;
    }

    public Employee addEmployee(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee updatedEmployee){
        return this.employeeRepository.save(updatedEmployee);
    }

    public void deleteEmployee(Employee employee){
        this.employeeRepository.delete(employee);
    }
}
