package me.kofesst.spring.carsaloon.repositories;

import me.kofesst.spring.carsaloon.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
