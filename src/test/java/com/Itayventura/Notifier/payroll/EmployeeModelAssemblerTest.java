package com.Itayventura.Notifier.payroll;

import com.Itayventura.Notifier.data.entity.Employee;
import com.Itayventura.Notifier.data.entity.Team;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeModelAssemblerTest {

    private final EmployeeModelAssembler assembler = new EmployeeModelAssembler();

    private Employee employee;

    @Before
    public void setUp() {
        employee = new Employee(0, "a", "b", "c@c.com", "R",new Team(0, "a", "b"));
    }

    @Test
    public void testToModel() {
        EntityModel<Employee> entityModel = assembler.toModel(employee);
        Employee newEmployee = entityModel.getContent();
        assertEquals(employee, newEmployee);

        Links links = entityModel.getLinks();

        Optional<Link> opt = links.getLink("self");
        assertTrue(opt.isPresent());

        Link self = opt.get();
        assertEquals("/employees/"+employee.getEmployeeId(), self.getHref());

        opt = links.getLink("employees");
        assertTrue(opt.isPresent());

        Link all = opt.get();
        assertEquals("/employees", all.getHref());
    }
}