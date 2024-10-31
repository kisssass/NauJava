package ru.kissass.NauJava.businesLogic;

import ru.kissass.NauJava.entity.User;

public interface UserService {
    void createUser(Long id, String login);
    User findById(Long id);
    void deleteById(Long id);
    void updateLogin(Long id, String newLogin);
}
