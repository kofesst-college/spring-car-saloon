package me.kofesst.spring.carsaloon.repositories;

import me.kofesst.spring.carsaloon.models.Car;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarsRepository extends CrudRepository<Car, Long> {
    List<Car> getByBrandContainsIgnoreCase(String brand);
}
