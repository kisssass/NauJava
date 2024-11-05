package ru.kissass.NauJava.accessLayer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.kissass.NauJava.entity.Task;
import ru.kissass.NauJava.entity.User;

import java.time.LocalDate;
import java.util.List;

@RepositoryRestResource(path = "tasks")
public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findByTitleAndUser(String header, User user);

    List<Task> findByTagsName(String name);

    List<Task> findByDateBetween(LocalDate start, LocalDate end);
}
