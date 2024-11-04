package ru.kissass.NauJava;

import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.kissass.NauJava.accessLayer.UserRepository;
import ru.kissass.NauJava.criteriaApi.UserRepositoryCustom;
import ru.kissass.NauJava.entity.User;

@SpringBootTest
public class UserTest {
    private final UserRepository userRepository;
    private final UserRepositoryCustom userRepositoryCustom;
    @Autowired
    UserTest(UserRepository userRepository, UserRepositoryCustom userRepositoryCustom)
    {
        this.userRepository = userRepository;
        this.userRepositoryCustom = userRepositoryCustom;
    }
    /**
     * Тестирование поиск пользователя по его имени
     */
    @Test
    void testFindUserByName()
    {
// генерация имени пользователя
        String userName = UUID.randomUUID().toString();
// создание пользователя
        User user = new User();
        user.setName(userName);
        userRepository.save(user);
// поиск пользователя по имени
        User foundUser = userRepositoryCustom.findByName(userName).getFirst();
// проверки
        Assertions.assertNotNull(foundUser);
        Assertions.assertEquals(user.getId(), foundUser.getId());
        Assertions.assertEquals(userName, foundUser.getName());
    }
}
