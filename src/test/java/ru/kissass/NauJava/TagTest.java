package ru.kissass.NauJava;


import java.util.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ru.kissass.NauJava.accessLayer.TagRepository;
import ru.kissass.NauJava.accessLayer.TaskRepository;
import ru.kissass.NauJava.businesLogic.TagService;
import ru.kissass.NauJava.entity.Tag;
import ru.kissass.NauJava.entity.Task;

@SpringBootTest
public class TagTest {
    private final TagService tagService;
    private final TaskRepository taskRepository;
    private final TagRepository tagRepository;
    @Autowired
    public TagTest(TagService tagService,
                    TaskRepository taskRepository,
                    TagRepository tagRepository)
    {
        this.tagService = tagService;
        this.taskRepository = taskRepository;
        this.tagRepository = tagRepository;
    }
    /**
     * Тестирование удаления задач вместе с тэгами
     */
    @Test
    void testDeleteTagInTx() {
// создание роли
        Tag tag = new Tag();
        tag.setName(UUID.randomUUID().toString());
        tagRepository.save(tag);
// создание задачи 1
        Task task1 = new Task();
        task1.setTitle(UUID.randomUUID().toString());
        Set<Tag> tags = new HashSet<>();
        tags.add(tag);
        task1.setTags(tags);
        taskRepository.save(task1);
// создание задачи 2
        Task task2 = new Task();
        task2.setTitle(UUID.randomUUID().toString());
        task2.setTags(tags);
        taskRepository.save(task2);
// удаление тега
        tagService.deleteTag(tag.getName());
// проверка отсутствия тэга в БД
        Optional<Tag> foundTag = tagRepository.findById(tag.getId());
        Assertions.assertTrue(foundTag.isEmpty());
// проверка отсутствия задачи 1 в БД
        Optional<Task> foundTask1 = taskRepository.findById(task1.getId());
        Assertions.assertTrue(foundTask1.isEmpty());
// проверка отсутствия задачи 2 в БД
        Optional<Task> foundTask2 = taskRepository.findById(task2.getId());
        Assertions.assertTrue(foundTask2.isEmpty());
    }
}
