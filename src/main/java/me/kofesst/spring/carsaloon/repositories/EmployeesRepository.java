package me.kofesst.spring.carsaloon.repositories;

import me.kofesst.spring.carsaloon.models.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeesRepository extends CrudRepository<Employee, Long> {
    Employee findByName(String name);
}
