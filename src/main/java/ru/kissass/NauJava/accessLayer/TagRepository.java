package ru.kissass.NauJava.accessLayer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.kissass.NauJava.entity.Tag;

@RepositoryRestResource(path = "tags")
public interface TagRepository extends CrudRepository<Tag, Long> {
    void deleteByTagName(String name);
}
