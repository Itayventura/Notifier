package com.Itayventura.Notifier.data.repository;

import com.Itayventura.Notifier.data.entity.Employee;
import com.Itayventura.Notifier.data.entity.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    public void setUpEmployee(){
        Team team = new Team(2, "sw2", "R&D");
        employee = new Employee(null, "David", "Levi","a@a.com", "software developer", team);
    }

    @Test
    public void testFindAllByTeam_TeamId() {
        testEntityManager.persist(employee);

        Iterable<Employee> employees = employeeRepository.findAllByTeam_TeamId(employee.getTeam().getTeamId());
        for (Employee foundEmployee : employees) {
            assertEquals(employee.getTeam(), foundEmployee.getTeam());
        }
    }

    @Test
    public void testFindByFirstNameAndLastName() {
        testEntityManager.persist(employee);
        Employee foundEmployee = employeeRepository.findByFirstNameAndLastName(employee.getFirstName(), employee.getLastName());
        assertNotNull(foundEmployee);
        assertEquals(employee, foundEmployee);
    }
}