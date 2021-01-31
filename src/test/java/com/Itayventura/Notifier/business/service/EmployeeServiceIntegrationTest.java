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
        Team team = new Team();
        team.setTeamId(1);
        team.setName("sw1");
        team.setDepartment("R&D");

        employee = new Employee();
        employee.setTeam(team);
        employee.setRoll("software developer");
        employee.setLastName("Levi");
        employee.setEmailAddress("a@a.com");
        employee.setFirstName("David");

        anotherEmployee = new Employee();
        anotherEmployee.setTeam(team);
        anotherEmployee.setRoll("software engineer");
        anotherEmployee.setLastName("Cohen");
        anotherEmployee.setEmailAddress("m@m.com");
        anotherEmployee.setFirstName("Moran");

        employeeService.addEmployee(anotherEmployee);
    }


    @After
    public void deleteEmployees(){
        Employee newEmployee = null;

        employeeService.deleteEmployee(employee);
        try{
            newEmployee = employeeService.getEmployeeByName(employee.getFirstName(), employee.getLastName());
        } catch (EmployeeNotFoundException ex){
            assertEquals("could not find employee " +
                    employee.getFirstName() + " " + employee.getLastName(), ex.getMessage());
        } finally{
            assertNull(newEmployee);
        }

        employeeService.deleteEmployee(anotherEmployee);
        try{
           newEmployee = employeeService.getEmployeeByName(anotherEmployee.getFirstName(), anotherEmployee.getLastName());
        } catch (EmployeeNotFoundException ex){
            assertEquals("could not find employee " +
                    anotherEmployee.getFirstName() + " " + anotherEmployee.getLastName(), ex.getMessage());
        } finally {
            assertNull(newEmployee);
        }

    }

    @Test
    public void testAddEmployee(){
        Employee newEmployee = employeeService.addEmployee(employee);
        assertNotNull(newEmployee);
        assertEquals(employee.getFirstName(), newEmployee.getFirstName());
        assertEquals(employee.getLastName(), newEmployee.getLastName());
        assertEquals(employee.getEmailAddress(), newEmployee.getEmailAddress());
        assertEquals(employee.getRoll(), newEmployee.getRoll());
        assertEquals(employee.getTeam().getTeamId(), newEmployee.getTeam().getTeamId());
        assertEquals(employee.getTeam().getDepartment(), newEmployee.getTeam().getDepartment());
        assertEquals(employee.getTeam().getName(), newEmployee.getTeam().getName());

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
        assertEquals(anotherEmployee.getFirstName(), newEmployee.getFirstName());
        assertEquals(anotherEmployee.getLastName(), newEmployee.getLastName());

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
        assertEquals(anotherEmployee.getRoll(), newEmployee.getRoll());
        assertEquals(anotherEmployee.getFirstName(), newEmployee.getFirstName());
        assertEquals(anotherEmployee.getEmailAddress(), newEmployee.getEmailAddress());
        assertEquals(anotherEmployee.getLastName(), newEmployee.getLastName());
        assertEquals(anotherEmployee.getTeam().getName(), newEmployee.getTeam().getName());
        assertEquals(anotherEmployee.getTeam().getDepartment(), newEmployee.getTeam().getDepartment());
    }

    @Test
    public void testGetEmployeeById(){
        Employee newEmployee = employeeService.getEmployeeById(anotherEmployee.getEmployeeId());
        assertNotNull(newEmployee);
        assertEquals(anotherEmployee.getRoll(), newEmployee.getRoll());
        assertEquals(anotherEmployee.getFirstName(), newEmployee.getFirstName());
        assertEquals(anotherEmployee.getEmailAddress(), newEmployee.getEmailAddress());
        assertEquals(anotherEmployee.getLastName(), newEmployee.getLastName());
        assertEquals(anotherEmployee.getTeam().getName(), newEmployee.getTeam().getName());
        assertEquals(anotherEmployee.getTeam().getDepartment(), newEmployee.getTeam().getDepartment());

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
        for (Employee newEmployee: teamEmployees){
            assertEquals(anotherEmployee.getTeam().getTeamId(), newEmployee.getTeam().getTeamId());
            assertEquals(anotherEmployee.getTeam().getDepartment(), newEmployee.getTeam().getDepartment());
            assertEquals(anotherEmployee.getTeam().getName(), newEmployee.getTeam().getName());
        }
    }

}