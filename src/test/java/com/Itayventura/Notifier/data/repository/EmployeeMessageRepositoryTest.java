package com.Itayventura.Notifier.data.repository;

import com.Itayventura.Notifier.data.entity.Employee;
import com.Itayventura.Notifier.data.entity.EmployeeMessage;
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

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeMessageRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private EmployeeMessageRepository employeeMessageRepository;

    private EmployeeMessage employeeMessage;
    private Employee employee;
    private Employee sender;

    @Before
    public void setUp() {
        sender = new Employee(2, "Mani", "mani", "c@c.com", "Team Leader", new Team(1, "sw1", "R&D"));
        employee = new Employee(1, "Itay", "Ventura", "a@a.com", "software developer", new Team(1, "sw1", "R&D"));
        employeeMessage = new EmployeeMessage(0, "content", sender, employee);
    }

    @Test
    public void findAllByEmployee() {
        testEntityManager.persist(employeeMessage);
        Iterable<EmployeeMessage> employeeMessages = employeeMessageRepository.findAllByEmployee(employee);
        Iterator<EmployeeMessage> it = employeeMessages.iterator();
        assertTrue(it.hasNext());
        while(it.hasNext()){
            EmployeeMessage employeeMessage = it.next();
            assertEquals(employee, employeeMessage.getEmployee());
        }
    }
}