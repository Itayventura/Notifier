package com.Itayventura.Notifier.data.repository;

import com.Itayventura.Notifier.data.entity.Employee;
import com.Itayventura.Notifier.data.entity.Team;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @Before
    public void setUpEmployee(){
        Team team = new Team();
        team.setTeamId(2);
        team.setName("sw2");
        team.setDepartment("R&D");
        employee = new Employee();
        employee.setTeam(team);
        employee.setRoll("software developer");
        employee.setLastName("Levi");
        employee.setEmailAddress("a@a.com");
        employee.setFirstName("David");

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