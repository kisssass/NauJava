package ru.kissass.NauJava.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kissass.NauJava.criteriaApi.UserRepositoryCustom;
import ru.kissass.NauJava.entity.User;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserRepositoryCustom userRepositoryCustom;

    @Autowired
    public UserController(UserRepositoryCustom userRepositoryCustom) {
        this.userRepositoryCustom = userRepositoryCustom;
    }

    @GetMapping("/findByName")
    public List<User> findByName(@RequestParam("name") String name) {
        return userRepositoryCustom.findByName(name);
    }

    @GetMapping("/findByTag")
    public List<User> findByTag(@RequestParam("tagName") String tagName) {
        return userRepositoryCustom.findByTag(tagName);
    }
}
