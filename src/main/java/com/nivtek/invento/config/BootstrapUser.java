package com.nivtek.invento.config;

import com.nivtek.invento.dao.UserRepository;
import com.nivtek.invento.model.Role;
import com.nivtek.invento.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/*
 * @project Invento
 * @author AsimSubedi created on 5/12/2020
 */
@Component
public class BootstrapUser implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    private UserRepository userRepository;


    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        System.out.println("===== VERIFYING IF USER EXISTS OR NOT =====");
        createAdminUser("nivtekdev", "nivtekdev", "niv@tek.dev", Role.ADMIN);
    }

    /**
     * Here we are creating the default admin user for our app only.
     * NOT Recommended in Production Ready Application!
     *
     * @param username
     * @param password
     * @param email
     * @param authority
     */
    private void createAdminUser(String username, String password, String email, Role authority) {
        if (userRepository.findByUsername(username) == null) {
            User user = new User(username, new BCryptPasswordEncoder().encode(password), email, authority, true);
            userRepository.save(user);
        }
    }
}
