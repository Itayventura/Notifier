package com.Itayventura.Notifier.business.service;

import com.Itayventura.Notifier.data.entity.Employee;
import com.Itayventura.Notifier.data.entity.Team;
import com.Itayventura.Notifier.data.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
        team.setName("sw1");
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

        verify(employeeRepository, times(1)).save(aMockEmployee);

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

        verify(employeeRepository, times(1)).findById((any(Integer.class)));


        assertNotNull(newEmployee);
        assertEquals(aMockEmployee.getFirstName(), newEmployee.getFirstName());
        assertEquals(aMockEmployee.getEmailAddress(), newEmployee.getEmailAddress());
        assertEquals(aMockEmployee.getLastName(), newEmployee.getLastName());
        assertEquals(aMockEmployee.getTeam(), newEmployee.getTeam());
        assertEquals(aMockEmployee.getRoll(), newEmployee.getRoll());
    }

    @Test
    public void testGetEmployeeByName() {

        when(employeeRepository.findByFirstNameAndLastName(any(String.class), any(String.class))).thenReturn(aMockEmployee);
        Employee newEmployee = employeeService.getEmployeeByName(aMockEmployee.getFirstName(), aMockEmployee.getLastName());

        verify(employeeRepository, times(1)).findByFirstNameAndLastName(any(String.class), any(String.class));


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

        verify(employeeRepository, times(1)).save(any(Employee.class));

        assertNotNull(newEmployee);

        assertEquals(aMockEmployee.getFirstName(), newEmployee.getFirstName());
    }

    @Test
    public void testDeleteEmployee(){
        employeeService.deleteEmployee(aMockEmployee);
        verify(employeeRepository, times(1)).delete(aMockEmployee);
    }

    @Test
    public void testGetTeamEmployees(){
        List<Employee> teamEmployees = new ArrayList<>();
        teamEmployees.add(aMockEmployee);
        when(employeeRepository.findAllByTeam_TeamId(any(Integer.class))).thenReturn(teamEmployees);
        List<Employee> newTeamEmployees = employeeService.getTeamEmployees(aMockEmployee.getTeam().getTeamId());

        verify(employeeRepository, times(1)).findAllByTeam_TeamId(any(Integer.class));

        assertTrue(!newTeamEmployees.isEmpty());

        for (Employee newEmployee: teamEmployees){
            assertEquals(aMockEmployee.getTeam().getTeamId(), newEmployee.getTeam().getTeamId());
            assertEquals(aMockEmployee.getTeam().getDepartment(), newEmployee.getTeam().getDepartment());
            assertEquals(aMockEmployee.getTeam().getName(), newEmployee.getTeam().getName());
        }


    }



}
