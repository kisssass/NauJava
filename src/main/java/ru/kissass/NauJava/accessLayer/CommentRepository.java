package ru.kissass.NauJava.accessLayer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.kissass.NauJava.entity.Category;
import ru.kissass.NauJava.entity.Comment;
import ru.kissass.NauJava.entity.Tag;
import ru.kissass.NauJava.entity.Task;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findByTask(Task task);

    @Query("SELECT r FROM Comment r WHERE r.task.taskId = :taskId")
    List<Comment> findByTaskId(Long taskId);
}
