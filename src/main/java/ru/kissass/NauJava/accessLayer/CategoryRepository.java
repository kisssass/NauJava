package ru.kissass.NauJava.accessLayer;

import org.springframework.data.repository.CrudRepository;
import ru.kissass.NauJava.entity.Category;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
