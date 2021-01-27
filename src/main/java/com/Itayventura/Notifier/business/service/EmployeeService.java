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
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployeeById(int id){
        return this.employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public Employee getEmployeeByName(String firstName, String lastName){
        Employee employee =  this.employeeRepository.findByFirstNameAndLastName(firstName, lastName);
        if (employee != null){
            return employee;
        } else {
            throw new EmployeeNotFoundException(firstName, lastName);
        }
    }

    public List<Employee> getEmployees(){
        Iterable<Employee> employees = this.employeeRepository.findAll();
        List<Employee> employeeList = new ArrayList<>();
        employees.iterator().forEachRemaining(employeeList::add);
        return employeeList;
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
            return this.employeeRepository.save(updatedEmployee);
        }
    }

    public void deleteEmployee(Employee employee){
        this.employeeRepository.delete(employee);
    }
}
