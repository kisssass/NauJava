package ru.kissass.NauJava.businesLogic;

import ru.kissass.NauJava.controller.exeptionHandler.UserAlreadyExistsException;
import ru.kissass.NauJava.entity.User;

public interface UserService {
    void addUser(User User) throws UserAlreadyExistsException;

}
