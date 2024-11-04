package ru.kissass.NauJava.accessLayer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.kissass.NauJava.entity.Category;
import ru.kissass.NauJava.entity.Tag;

import java.util.List;

public interface TagRepository extends CrudRepository<Tag, Long> {
   void deleteByTagName(String tagName);
}
