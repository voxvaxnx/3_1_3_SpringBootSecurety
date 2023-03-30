package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;
import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Initializer {

    private final UserService userService;

    @Autowired
    public Initializer(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        User user = userService.getByUserName("admin");
        if (user == null) {
            User admin = new User("admin@mail.ru", "admin", "admin");
            Role userRole = new Role("ROLE_USER");
            Role adminRole = new Role("ROLE_ADMIN");
            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(userRole);
            adminRoles.add(adminRole);
            admin.setRoles(adminRoles);
            userService.addUser(admin);
        }
    }
}