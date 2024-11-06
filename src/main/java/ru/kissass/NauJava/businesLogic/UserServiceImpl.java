package ru.kissass.NauJava.businesLogic;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kissass.NauJava.accessLayer.UserRepository;
import ru.kissass.NauJava.controller.exeptionHandler.UserAlreadyExistsException;
import ru.kissass.NauJava.entity.Role;
import ru.kissass.NauJava.entity.User;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addUser(User user) throws UserAlreadyExistsException {
        User userFromRepository = userRepository.findByUserName(user.getName());
        if (userFromRepository != null) {
            throw new UserAlreadyExistsException();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
    }

}
