package me.kofesst.spring.carsaloon.repositories;

import me.kofesst.spring.carsaloon.models.Car;
import me.kofesst.spring.carsaloon.models.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomersRepository extends CrudRepository<Customer, Long> {
    List<Customer> getByCustomerFirstnameContainsIgnoreCase(String customerFirstname);

    List<Customer> getByCar(Car car);
}
