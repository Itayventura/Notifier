package com.Itayventura.Notifier.data.repository;

import com.Itayventura.Notifier.data.entity.Employee;
import com.Itayventura.Notifier.data.entity.EmployeeMessage;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeMessageRepository extends MessageRepository<EmployeeMessage> {
    Iterable<EmployeeMessage> findAllByEmployee(Employee employee);
}
