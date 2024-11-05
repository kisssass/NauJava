package ru.kissass.NauJava.accessLayer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.kissass.NauJava.entity.Category;
import ru.kissass.NauJava.entity.Tag;

import java.util.List;

@RepositoryRestResource(path = "tags")
public interface TagRepository extends CrudRepository<Tag, Long> {
   void deleteByTagName(String name);
}
