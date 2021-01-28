package com.Itayventura.Notifier.business.service;

import com.Itayventura.Notifier.data.entity.Employee;
import com.Itayventura.Notifier.data.entity.Team;
import com.Itayventura.Notifier.data.repository.EmployeeRepository;
import com.Itayventura.Notifier.payroll.EmployeeNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class EmployeeServiceUnitTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Autowired
    @InjectMocks
    private EmployeeService employeeService;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    private Employee aMockEmployee;

    @Before
    public void setUpEmployee(){
        Team team = new Team();
        team.setTeamId(1);
        team.setTeamName("sw1");
        team.setDepartment("R&D");

        aMockEmployee = new Employee();
        aMockEmployee.setEmployeeId(1);
        aMockEmployee.setTeam(team);
        aMockEmployee.setRoll("software developer");
        aMockEmployee.setLastName("Ventura");
        aMockEmployee.setEmailAddress("a@a.com");
        aMockEmployee.setFirstName("Itay");
    }


    @Test
    public void testAddEmployee(){

        when(employeeRepository.save(any(Employee.class))).thenReturn(aMockEmployee);

        Employee newEmployee = employeeService.addEmployee(aMockEmployee);

        assertNotNull(newEmployee);
        assertEquals(aMockEmployee.getFirstName(), newEmployee.getFirstName());
        assertEquals(aMockEmployee.getEmailAddress(), newEmployee.getEmailAddress());
        assertEquals(aMockEmployee.getLastName(), newEmployee.getLastName());
        assertEquals(aMockEmployee.getTeam(), newEmployee.getTeam());
        assertEquals(aMockEmployee.getRoll(), newEmployee.getRoll());
    }
    @Test
    public void testGetEmployeeById(){

        when(employeeRepository.findById(any(Integer.class))).thenReturn(java.util.Optional.of(aMockEmployee));
        Employee newEmployee = employeeService.getEmployeeById(aMockEmployee.getEmployeeId());
        assertNotNull(newEmployee);

        assertEquals(aMockEmployee.getFirstName(), newEmployee.getFirstName());
        assertEquals(aMockEmployee.getEmailAddress(), newEmployee.getEmailAddress());
        assertEquals(aMockEmployee.getLastName(), newEmployee.getLastName());
        assertEquals(aMockEmployee.getTeam(), newEmployee.getTeam());
        assertEquals(aMockEmployee.getRoll(), newEmployee.getRoll());
    }

    @Test
    public void getEmployeeByName() {

        when(employeeRepository.findByFirstNameAndLastName(any(String.class), any(String.class))).thenReturn(aMockEmployee);
        Employee newEmployee = employeeService.getEmployeeByName(aMockEmployee.getFirstName(), aMockEmployee.getLastName());
        assertNotNull(newEmployee);

        assertEquals(aMockEmployee.getFirstName(), newEmployee.getFirstName());
        assertEquals(aMockEmployee.getEmailAddress(), newEmployee.getEmailAddress());
        assertEquals(aMockEmployee.getLastName(), newEmployee.getLastName());
        assertEquals(aMockEmployee.getTeam(), newEmployee.getTeam());
        assertEquals(aMockEmployee.getRoll(), newEmployee.getRoll());
    }

    @Test
    public void testUpdateEmployee(){
        when(employeeRepository.save(any(Employee.class))).thenReturn(aMockEmployee);
        aMockEmployee.setFirstName("changed");
        Employee newEmployee = employeeService.updateEmployee(aMockEmployee);
        assertNotNull(newEmployee);

        assertEquals(aMockEmployee.getFirstName(), newEmployee.getFirstName());
    }

    @Test
    public void testDeleteEmployee(){
        employeeService.deleteEmployee(aMockEmployee);
        try {
            Employee newEmployee = employeeService.getEmployeeById(aMockEmployee.getEmployeeId());
        } catch (EmployeeNotFoundException ex){
            assertEquals("could not find employee " + aMockEmployee.getEmployeeId() , ex.getMessage());
        }

    }


}
