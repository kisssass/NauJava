package ru.kissass.NauJava.accessLayer;

import org.springframework.data.repository.CrudRepository;
import ru.kissass.NauJava.entity.Task;
import ru.kissass.NauJava.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findByTitleAndUser(String header, User user);

    List<Task> findByTagsName(String tagName);

    List<Task> findByDateBetween(LocalDate start, LocalDate end);
}
