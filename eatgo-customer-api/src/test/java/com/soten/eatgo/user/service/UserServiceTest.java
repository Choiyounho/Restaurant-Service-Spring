package com.soten.eatgo.user.service;

import com.soten.eatgo.global.exception.EmailExistedException;
import com.soten.eatgo.global.exception.EmailNotExistedException;
import com.soten.eatgo.global.exception.PasswordWrongException;
import com.soten.eatgo.user.domain.User;
import com.soten.eatgo.user.domain.UserRepository;
import com.soten.eatgo.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class UserServiceTest {

    private String email;
    private String name;
    private String password;

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        email = "maxosa@naver.com";
        name = "younho";
        password = "test";

        userService = new UserService(userRepository, passwordEncoder);
    }

    @Test
    @DisplayName("회원등록")
    void registerUser() {
        userService.registerUser(email, name, password);

        verify(userRepository).save(any());
    }

    @Test
    @DisplayName("회원등록 실패")
    void registerUserWithExistedEmail() {
        User user = User.builder().build();

        given(userRepository.findByEmail(email)).willReturn(Optional.ofNullable(user));

        assertThatExceptionOfType(EmailExistedException.class)
                .isThrownBy(() -> { userService.registerUser(email, name, password); });
    }

}
