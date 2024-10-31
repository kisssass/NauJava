package ru.kissass.NauJava.businesLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kissass.NauJava.accessLayer.UserRepository;
import ru.kissass.NauJava.entity.User;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }
    @Override
    public void createUser(Long id, String login)
    {
        User newUser = new User();
        newUser.setId(id);
        newUser.setLogin(login);
        userRepository.create(newUser);
    }
    @Override
    public User findById(Long id)
    {
        return userRepository.read(id);
    }
    @Override
    public void deleteById(Long id)
    {
        userRepository.delete(id);
    }
    @Override
    public void updateLogin(Long id, String newLogin)
    {
        User user = new User();
        user.setId(id);
        user.setLogin(newLogin);
        userRepository.update(user);
    }
}
