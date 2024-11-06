package ru.kissass.NauJava.controller.exeptionHandler;

public class UserAlreadyExistsException extends java.lang.Exception {
    public UserAlreadyExistsException() {
        super("User already exists");
    }
}
