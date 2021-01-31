package com.Itayventura.Notifier.business.service;

import com.Itayventura.Notifier.data.entity.Employee;
import com.Itayventura.Notifier.data.entity.Team;
import com.Itayventura.Notifier.payroll.EmployeeNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class EmployeeServiceIntegrationTest {

    @Autowired
    private EmployeeService employeeService;

    private Employee employee;
    private Employee anotherEmployee;

    @Before
    public void setUpEmployees(){
        Team team = new Team(1, "sw1", "R&D");
        employee = new Employee(null, "David", "Levi", "a@a.com", "software developer", team);
        anotherEmployee = new Employee(null, "Moran", "Cohen", "m@m.com", "software engineer", team);
        employeeService.addEmployee(anotherEmployee);
    }

    @After
    public void deleteEmployees(){
        employeeService.deleteEmployee(employee);
        employeeService.deleteEmployee(anotherEmployee);

    }

    @Test
    public void testAddEmployee(){
        Employee newEmployee = employeeService.addEmployee(employee);
        assertNotNull(newEmployee);
        assertEquals(employee, newEmployee);
    }

    @Test (expected = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void testAddEmployeeToNotExistingTeam(){
        employee.setTeam(new Team());
        employeeService.addEmployee(employee);
    }

    @Test
    public void testUpdateEmployee(){
        anotherEmployee.setFirstName("Not " + anotherEmployee.getFirstName());
        anotherEmployee.setLastName("Not " + anotherEmployee.getLastName());
        Employee newEmployee = employeeService.updateEmployee(anotherEmployee);

        assertNotNull(newEmployee);
        assertEquals(anotherEmployee, newEmployee);

    }

    @Test public void testUpdateNotExistingEmployee(){
        Employee newEmployee = employeeService.updateEmployee(employee);
        assertNotNull(newEmployee);
        assertEquals(employee, newEmployee);
    }

    @Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
    public void testUpdateEmployeeWithMissingAttributes(){
        anotherEmployee.setEmailAddress(null);
        employeeService.updateEmployee(anotherEmployee);

    }

    @Test(expected = EmployeeNotFoundException.class)
    public void testDeleteEmployee(){
        employeeService.deleteEmployee(anotherEmployee);
        employeeService.getEmployeeByName(anotherEmployee.getFirstName(), anotherEmployee.getLastName());
    }


    @Test
    public void testGetEmployeeByName(){
        Employee newEmployee = employeeService.getEmployeeByName(anotherEmployee.getFirstName(), anotherEmployee.getLastName());
        assertNotNull(newEmployee);
        assertEquals(anotherEmployee, newEmployee);
    }

    @Test
    public void testGetEmployeeById(){
        Employee newEmployee = employeeService.getEmployeeById(anotherEmployee.getEmployeeId());
        assertNotNull(newEmployee);
        assertEquals(anotherEmployee, newEmployee);
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void testGetNotExistingEmployeeById(){
        employeeService.getEmployeeById(Integer.MAX_VALUE);
    }

    @Test(expected = EmployeeNotFoundException.class)
    public void testGetNotExistingEmployeeByName(){
        employeeService.getEmployeeByName("not", "exist");
    }

    @Test
    public void testGetTeamEmployees(){
        List<Employee> teamEmployees = employeeService.getTeamEmployees(anotherEmployee.getTeam().getTeamId());
        assertFalse(teamEmployees.isEmpty());
        teamEmployees.forEach(newEmployee -> assertEquals(anotherEmployee.getTeam(), newEmployee.getTeam()));
    }

}