package com.jaime.recipes.service;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.jaime.recipes.error.UserAlreadyExistException;
import com.jaime.recipes.model.Role;
import com.jaime.recipes.model.User;
import com.jaime.recipes.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findFirstByEmail(email);
    }

    @Transactional
    @Modifying
    public void registerNewUser(String email, String password) {
        String emailInLowerCase = email.toLowerCase();
        User user = new User(emailInLowerCase, password, Role.USER);
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new UserAlreadyExistException();
        }
    }
}
