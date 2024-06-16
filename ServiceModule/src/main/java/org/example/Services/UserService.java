package org.example.Services;

import org.example.DataAccessLayer.AppUserRepository;
import org.example.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserService implements UserDetailsService {
    private final AppUserRepository userRepository;

    @Autowired
    public UserService(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Создание коллекции GrantedAuthority с помощью роли пользователя
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));

        // Создание объекта UserDetails с использованием роли и других данных пользователя
        return new User(user.getUsername(), user.getPassword(), authorities);
    }

    public AppUser getAppUser(String username) {
        return userRepository.findByUsername(username);
    }


    public AppUser addUser(AppUser appUser) {
        userRepository.save(appUser);
        return appUser;
    }
}