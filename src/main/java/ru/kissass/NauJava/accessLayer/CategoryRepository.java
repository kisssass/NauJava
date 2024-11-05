package ru.kissass.NauJava.accessLayer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.kissass.NauJava.entity.Category;

import java.util.List;

@RepositoryRestResource(path = "categories")
public interface CategoryRepository extends CrudRepository<Category, Long> {
}

