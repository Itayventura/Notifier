package com.Itayventura.Notifier.data.repository;

import com.Itayventura.Notifier.data.entity.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    Iterable<Employee> findAllByTeamId(long id);
}
