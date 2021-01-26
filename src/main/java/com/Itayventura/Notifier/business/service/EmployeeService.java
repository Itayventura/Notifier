package com.Itayventura.Notifier.business.service;

import com.Itayventura.Notifier.data.entity.Employee;
import com.Itayventura.Notifier.data.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


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

    public Employee addEmployee(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee updatedEmployee){
        Optional<Employee> opt = this.employeeRepository.findById(updatedEmployee.getEmployeeId());
        if (opt.isPresent()){
            Employee employee = opt.get();
            employee.setEmailAddress(updatedEmployee.getEmailAddress());
            employee.setFirstName(updatedEmployee.getFirstName());
            employee.setLastName(updatedEmployee.getLastName());
            employee.setRoll(updatedEmployee.getRoll());
            employee.setTeam(updatedEmployee.getTeam());
            return this.employeeRepository.save(employee);
        } else{
            return null;
        }
    }

    public void deleteEmployee(Employee employee){
        this.employeeRepository.delete(employee);
    }
}
