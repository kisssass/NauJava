package ru.kissass.NauJava.criteriaApi;

import ru.kissass.NauJava.entity.User;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> findByName(String name);

    List<User> findByTag(String tagTitle);
}
