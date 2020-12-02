package com.soten.eatgo.user.service;

import com.soten.eatgo.global.exception.EmailExistedException;
import com.soten.eatgo.global.exception.EmailNotExistedException;
import com.soten.eatgo.global.exception.PasswordWrongException;
import com.soten.eatgo.user.domain.User;
import com.soten.eatgo.user.domain.UserRepository;
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
    void authenticateWithValidAttributes() {
        User mockUser = User.builder()
                .email(email)
                .build();

        given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));

        given(passwordEncoder.matches(any(), any())).willReturn(true);

        User user = userService.authenticate(email, password);

        assertThat(user.getEmail()).isEqualTo(email);
    }

    @Test
    @DisplayName("이메일이 틀린 경우")
    void authenticateWithNotExistedEmail() {
        email = "x@naver.com";

        given(userRepository.findByEmail(email)).willReturn(Optional.empty());

        assertThatExceptionOfType(EmailNotExistedException.class)
                .isThrownBy(() -> { userService.authenticate(email, password);} );
    }

    @Test
    @DisplayName("비밀번호가 틀린 경우")
    void authenticateWithWrongPassword() {
        password = "x";

        User mockUser = User.builder()
                .email(email)
                .build();

        given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));

        given(passwordEncoder.matches(any(), any())).willReturn(false);

        assertThatExceptionOfType(PasswordWrongException.class)
                .isThrownBy(() -> userService.authenticate(email, password));
    }

}
