package ru.kissass.NauJava.businesLogic;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kissass.NauJava.accessLayer.UserRepository;
import ru.kissass.NauJava.entity.Role;
import ru.kissass.NauJava.entity.User;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository UserRepository;

    public UserDetailsServiceImpl(UserRepository UserRepository) {
        this.UserRepository = UserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = UserRepository.findByUserName(username);

        if (user != null) {
            return new
                    org.springframework.security.core.userdetails.User(
                    user.getName(), user.getPassword(), mapRoleToAuthority(user.getRole()));
        }
        throw new UsernameNotFoundException(username);

    }

    private Collection<GrantedAuthority> mapRoleToAuthority(Role role) {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }
}
