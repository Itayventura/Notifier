package com.Itayventura.Notifier.controller;

import com.Itayventura.Notifier.business.service.EmployeeService;
import com.Itayventura.Notifier.business.service.TeamEmployeesService;
import com.Itayventura.Notifier.data.entity.Employee;
import com.Itayventura.Notifier.data.entity.Team;
import com.Itayventura.Notifier.payroll.EmployeeModelAssembler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
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

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private Employee aMockEmployee;

    @BeforeEach
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
        aMockEmployee.setFirstName("Itay");    }

    @Test
    public void testAddEmployee() throws Exception {

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
    public void testAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/employees")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void testOne() throws Exception {

        when(employeeService.getEmployeeById(any(Integer.class))).thenReturn(aMockEmployee);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/employees/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testOneNotFound() throws Exception {
        when(employeeService.getEmployeeById(any(Integer.class))).thenReturn(null);


        mockMvc.perform(MockMvcRequestBuilders
                .get("/employees/" + Integer.MAX_VALUE)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }



    @Test
    public void testUpdateEmployee() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        when(employeeService.updateEmployee(any(Employee.class))).thenReturn(aMockEmployee);

        mockMvc.perform(MockMvcRequestBuilders.put("/employees/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(aMockEmployee))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testUpdateIllegalEmployee() throws Exception {
        when(employeeService.updateEmployee(any(Employee.class))).thenReturn(null);
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(MockMvcRequestBuilders.put("/employees/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Employee()))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(MockMvcRequestBuilders.delete("/employees/delete")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(aMockEmployee)))
                .andExpect(status().isNoContent());
    }

    @Test
    public void getTeamEmployees() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/employees/team/"+aMockEmployee.getTeam().getName())

                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}