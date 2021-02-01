package com.Itayventura.Notifier.data.repository;

import com.Itayventura.Notifier.data.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    Iterable<Employee> findAllByTeam_TeamId(int id);
    Employee findByFirstNameAndLastName(String firstName, String lastName);
}
