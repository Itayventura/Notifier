package com.Itayventura.Notifier.controller;

import com.Itayventura.Notifier.business.service.EmployeeService;
import com.Itayventura.Notifier.data.entity.Employee;
import com.Itayventura.Notifier.data.entity.Team;
import com.Itayventura.Notifier.payroll.EmployeeModelAssembler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

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
        Team team = new Team(0, "sw1", "R&D");
        aMockEmployee = new Employee(null, "Itay", "ventura", "a@a.com", "software developer", team);
    }

    @Test
    public void testAddEmployee() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        when(employeeService.addEmployee(any(Employee.class))).thenReturn(aMockEmployee);

        mockMvc.perform(MockMvcRequestBuilders.post("/employees/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(aMockEmployee))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testAddEmptyEmployee() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        when(employeeService.addEmployee(any(Employee.class))).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/employees/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Employee()))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/employees")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void testGetEmployeeById() throws Exception {

        when(employeeService.getEmployeeById(any(Integer.class))).thenReturn(aMockEmployee);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/employees/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void testGetEmployeeByIdNotFound() throws Exception {
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
                .get("/employees/team/"+aMockEmployee.getTeam().getTeamId())

                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}