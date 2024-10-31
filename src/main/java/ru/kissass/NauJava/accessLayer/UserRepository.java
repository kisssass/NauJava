package ru.kissass.NauJava.accessLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kissass.NauJava.entity.User;

import java.util.List;


@Component
public class UserRepository implements CrudRepository<User, Long>{
    private final List<User> userContainer;
    @Autowired
    public UserRepository(List<User> userContainer)
    {
        this.userContainer = userContainer;
    }
    @Override
    public void create(User user)
    {
        userContainer.add(user);
    }
    @Override
    public User read(Long id)
    {
        return userContainer
                .stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);    }
    @Override
    public void update(User user)
    {
        for (int i = 0; i < userContainer.size(); i++) {
            if (user.getId().equals(userContainer.get(i).getId())) {
                userContainer.set(i, user);
            }
        }
    }
    @Override
    public void delete(Long id)
    {
        userContainer.removeIf(user -> user.getId().equals(id));
    }
}
