package com.Itayventura.Notifier.business.service;

import com.Itayventura.Notifier.data.entity.Employee;
import com.Itayventura.Notifier.data.entity.Team;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class EmployeeServiceIntegrationTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void testAddEmployee(){
        Team team = new Team();
        team.setTeamId(1);
        team.setTeamName("sw1");
        team.setDepartment("R&D");

        Employee employee = new Employee();
        employee.setTeam(team);
        employee.setEmployeeId(1);
        employee.setRoll("software developer");
        employee.setLastName("Levi");
        employee.setEmailAddress("a@a.com");
        employee.setFirstName("David");

        Employee newEmployee = employeeService.addEmployee(employee);

        assertNotNull(newEmployee);
        assertEquals(1, newEmployee.getEmployeeId());
        assertEquals("David", newEmployee.getFirstName());
        assertEquals("Levi", newEmployee.getLastName());
        assertEquals("a@a.com", newEmployee.getEmailAddress());
        assertEquals("software developer", newEmployee.getRoll());
        assertNotNull(newEmployee.getTeam());
    }

    @Test (expected = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void testAddEmployeeToNotExistingTeam(){
        Employee employee = new Employee();
        employee.setTeam(new Team());
        employee.setEmployeeId(1);
        employee.setRoll("software developer");
        employee.setLastName("Levi");
        employee.setEmailAddress("a@a.com");
        employee.setFirstName("David");
        Employee newEmployee = employeeService.addEmployee(employee);

    }

    @Test
    public void testUpdateEmployee(){
        Team team = new Team();
        team.setTeamId(1);
        team.setTeamName("sw1");
        team.setDepartment("R&D");

        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setFirstName("Not Itay");
        employee.setLastName("Not Ventura");
        employee.setRoll("Not Software Developer");
        employee.setTeam(team);
        employee.setEmailAddress("Not a@a.com");

        Employee newEmployee = employeeService.updateEmployee(employee);

        assertNotNull(newEmployee);
        assertEquals("Not Itay", newEmployee.getFirstName());



    }

    @Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
    public void testUpdateEmployeeWithMissingAttributes(){
        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setFirstName("Not Itay");

        Employee newEmployee = employeeService.updateEmployee(employee);

        assertNotNull(newEmployee);
        assertEquals("Not Itay", newEmployee.getFirstName());

    }

}