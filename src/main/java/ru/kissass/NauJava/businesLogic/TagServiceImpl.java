package ru.kissass.NauJava.businesLogic;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ru.kissass.NauJava.accessLayer.TagRepository;
import ru.kissass.NauJava.accessLayer.TaskRepository;
import ru.kissass.NauJava.entity.Task;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final TaskRepository taskRepository;
    private final PlatformTransactionManager transactionManager;

    public TagServiceImpl(TagRepository tagRepository, TaskRepository taskRepository,
                          PlatformTransactionManager transactionManager) {
        this.tagRepository = tagRepository;
        this.taskRepository = taskRepository;
        this.transactionManager = transactionManager;
    }

    @Override
    public void deleteTag(String name) {
        TransactionStatus status = transactionManager.getTransaction(new
                DefaultTransactionDefinition());
        try {
            List<Task> tasks = taskRepository.findByTagsName(name);
            for (Task task : tasks) {
                taskRepository.delete(task);
            }
            tagRepository.deleteByTagName(name);
            transactionManager.commit(status);
        } catch (DataAccessException ex) {
            transactionManager.rollback(status);
            throw ex;
        }
    }

}
