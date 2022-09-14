package me.kofesst.spring.carsaloon.repositories;

import me.kofesst.spring.carsaloon.models.Position;
import org.springframework.data.repository.CrudRepository;

public interface PositionsRepository extends CrudRepository<Position, Long> {
    Position findByName(String name);
}
