package com.soten.eatgo.user.service;

import com.soten.eatgo.global.exception.EmailExistedException;
import com.soten.eatgo.global.exception.EmailNotExistedException;
import com.soten.eatgo.global.exception.PasswordWrongException;
import com.soten.eatgo.user.domain.User;
import com.soten.eatgo.user.domain.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String email, String name, String password) {
        Optional<User> existed = userRepository.findByEmail(email);

        if (existed.isPresent()) {
            throw new EmailExistedException(email);
        }

        String encodedPassword = passwordEncoder.encode(password);

        User user = User.builder()
                .id(1004L)
                .email(email)
                .name(name)
                .password(encodedPassword)
                .level(1L)
                .build();

        return userRepository.save(user);
    }

    public User authenticate(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new EmailNotExistedException(email));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new PasswordWrongException();
        }

        return user;
    }

}
