package com.Itayventura.Notifier.business.service;

import com.Itayventura.Notifier.data.entity.Employee;
import com.Itayventura.Notifier.data.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployee(String firstName, String lastName){
        return this.employeeRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public Iterable<Employee> getEmployees(){
        return this.employeeRepository.findAll();
    }

    public void addEmployee(Employee employee) {
        this.employeeRepository.save(employee);
    }
}
