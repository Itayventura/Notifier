package com.Itayventura.Notifier.business.service;

import com.Itayventura.Notifier.data.entity.Employee;
import com.Itayventura.Notifier.data.entity.Team;
import org.junit.Assert;
import org.junit.Before;
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

    private Employee employee;

    @Before
    public void setUp(){
        Team team = new Team();
        team.setTeamId(1);
        team.setTeamName("sw1");
        team.setDepartment("R&D");

        employee = new Employee();
        employee.setEmployeeId(4);
        employee.setTeam(team);
        employee.setRoll("software developer");
        employee.setLastName("Levi");
        employee.setEmailAddress("a@a.com");
        employee.setFirstName("David");
    }

    @Test
    public void testAddEmployee(){
        Employee newEmployee = employeeService.addEmployee(employee);
        assertNotNull(newEmployee);
        employee.setEmployeeId(newEmployee.getEmployeeId());

        assertNotNull(newEmployee);
        assertEquals(employee.getFirstName(), newEmployee.getFirstName());
        assertEquals(employee.getLastName(), newEmployee.getLastName());
        assertEquals(employee.getEmailAddress(), newEmployee.getEmailAddress());
        assertEquals(employee.getRoll(), newEmployee.getRoll());
        assertEquals(employee.getTeam().getTeamId(), newEmployee.getTeam().getTeamId());
        assertEquals(employee.getTeam().getDepartment(), newEmployee.getTeam().getDepartment());
        assertEquals(employee.getTeam().getName(), newEmployee.getTeam().getName());

        employeeService.deleteEmployee(employee);

        newEmployee = employeeService.getEmployee(employee.getFirstName(), employee.getLastName());
        assertNull(newEmployee);
    }

    @Test (expected = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void testAddEmployeeToNotExistingTeam(){
        Team team = employee.getTeam();
        try{
            employee.setTeam(new Team());
            Employee newEmployee = employeeService.addEmployee(employee);
            assertNotNull(newEmployee);
            employee.setEmployeeId(newEmployee.getEmployeeId());

            assertNotNull(newEmployee.getTeam());

        } finally {
            employee.setTeam(team);
            assertEquals(team, employee.getTeam());
        }

    }

    @Test
    public void testUpdateEmployee(){
        Employee newEmployee = employeeService.addEmployee(employee);
        assertNotNull(newEmployee);
        employee.setEmployeeId(newEmployee.getEmployeeId());

        newEmployee.setFirstName("Not " + employee.getFirstName());
        newEmployee.setLastName("Not " + employee.getLastName());
        newEmployee = employeeService.updateEmployee(newEmployee);

        assertNotNull(newEmployee);
        assertEquals("Not " + employee.getFirstName(), newEmployee.getFirstName());
        assertEquals("Not " + employee.getLastName(), newEmployee.getLastName());

        employeeService.deleteEmployee(newEmployee);
        newEmployee = employeeService.getEmployee(newEmployee.getFirstName(), newEmployee.getLastName());
        assertNull(newEmployee);
    }

    @Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
    public void testUpdateEmployeeWithMissingAttributes(){
        Employee newEmployee = employeeService.addEmployee(employee);
        assertNotNull(newEmployee);
        employee.setEmployeeId(newEmployee.getEmployeeId());

        String firstName = newEmployee.getFirstName();
        try{
            newEmployee.setFirstName(null);
            newEmployee = employeeService.updateEmployee(newEmployee);

        } finally {
            newEmployee = employeeService.getEmployee(employee.getFirstName(), employee.getLastName());
            assertNotNull(newEmployee);
            assertNotNull(newEmployee.getFirstName());
            employeeService.deleteEmployee(newEmployee);
            newEmployee = employeeService.getEmployee(employee.getFirstName(), employee.getLastName());
            assertNull(newEmployee);
        }
    }

    @Test
    public void testDeleteEmployee(){
        Employee newEmployee = employeeService.addEmployee(employee);
        assertNotNull(newEmployee);
        employee.setEmployeeId(newEmployee.getEmployeeId());
        employeeService.deleteEmployee(employee);
        newEmployee = employeeService.getEmployee(employee.getFirstName(), employee.getLastName());
        assertNull(newEmployee);
    }

    @Test
    public void getEmployee(){
        Employee newEmployee = employeeService.addEmployee(employee);
        assertNotNull(newEmployee);
        employee.setEmployeeId(newEmployee.getEmployeeId());
        newEmployee = employeeService.getEmployee(employee.getFirstName(), employee.getLastName());
        assertNotNull(newEmployee);
        assertEquals(employee.getRoll(), newEmployee.getRoll());
        assertEquals(employee.getFirstName(), newEmployee.getFirstName());
        assertEquals(employee.getEmailAddress(), newEmployee.getEmailAddress());
        assertEquals(employee.getLastName(), newEmployee.getLastName());
        assertEquals(employee.getTeam().getName(), newEmployee.getTeam().getName());
        assertEquals(employee.getTeam().getDepartment(), newEmployee.getTeam().getDepartment());

        employeeService.deleteEmployee(newEmployee);
        newEmployee = employeeService.getEmployee(employee.getFirstName(), employee.getLastName());
        assertNull(newEmployee);
    }

    @Test
    public void getNotExistingEmployee(){
        Employee newEmployee = employeeService.getEmployee("not", "exist");
        assertNull(newEmployee);
    }


}