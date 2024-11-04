package ru.kissass.NauJava.criteriaApi;

import java.util.List;
import ru.kissass.NauJava.entity.User;

public interface UserRepositoryCustom {
    List<User> findByName(String name);

    List<User> findByTag(String tagTitle);
}
