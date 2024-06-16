package org.example.securityConfs;

import org.example.DataAccessLayer.AppUserRepository;
import org.example.entities.AppUser;
import org.example.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RepoInitializer implements CommandLineRunner {
    private final AppUserRepository userRepository;

    @Autowired
    public RepoInitializer(AppUserRepository userRepository ) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        if (!userRepository.existsByRole(Role.ADMIN)) {
            // Создаем нового пользователя администратора
            AppUser admin = new AppUser();
            admin.setUsername("admin");
            admin.setPassword("123");
            admin.setRole(Role.ADMIN);

            // Сохраняем пользователя в базе данных
            userRepository.save(admin);
        }
    }
}
