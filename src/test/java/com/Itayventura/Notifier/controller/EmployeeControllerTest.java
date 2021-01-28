package com.Itayventura.Notifier.controller;

import com.Itayventura.Notifier.business.service.EmployeeService;
import com.Itayventura.Notifier.business.service.TeamEmployeesService;
import com.Itayventura.Notifier.data.entity.Employee;
import com.Itayventura.Notifier.data.entity.Team;
import com.Itayventura.Notifier.payroll.EmployeeModelAssembler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private TeamEmployeesService teamEmployeesService;

    @MockBean
    private EmployeeModelAssembler assembler;

    @InjectMocks
    private EmployeeController employeeController;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    public void addEmployee() throws Exception {
        Team team = new Team();
        team.setTeamId(1);
        team.setTeamName("sw1");
        team.setDepartment("R&D");

        Employee aMockEmployee = new Employee();
        aMockEmployee.setEmployeeId(1);
        aMockEmployee.setTeam(team);
        aMockEmployee.setRoll("software developer");
        aMockEmployee.setLastName("Ventura");
        aMockEmployee.setEmailAddress("a@a.com");
        aMockEmployee.setFirstName("Itay");

        ObjectMapper objectMapper = new ObjectMapper();

        when(employeeService.addEmployee(any(Employee.class))).thenReturn(aMockEmployee);

        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(aMockEmployee))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testAddEmptyEmployee() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        when(employeeService.addEmployee(any(Employee.class))).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Employee()))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    void all() {
    }

    @Test
    void one() {
    }



    @Test
    void updateEmployee() {
    }

    @Test
    void deleteEmployee() {
    }

    @Test
    void getTeamEmployees() {
    }
}