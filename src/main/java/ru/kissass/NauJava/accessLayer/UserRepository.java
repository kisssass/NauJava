package ru.kissass.NauJava.accessLayer;

import org.springframework.data.repository.CrudRepository;
import ru.kissass.NauJava.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

}