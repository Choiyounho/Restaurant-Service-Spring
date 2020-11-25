package com.soten.eatgo.user;

import com.soten.eatgo.global.exception.EmailExistedException;
import com.soten.eatgo.user.domain.User;
import com.soten.eatgo.user.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        userService = new UserService(userRepository);
    }

    @Test
    @DisplayName("회원등록")
    void registerUser() {
        String email = "maxosa@naver.com";
        String name = "younho";
        String password = "test";

        userService.registerUser(email, name, password);

        verify(userRepository).save(any());
    }

    @Test
    @DisplayName("회원등록 실패")
    void registerUserWithExistedEmail() {
        String email = "maxosa@naver.com";
        String name = "younho";
        String password = "test";

        User user = User.builder().build();

        given(userRepository.findByEmail(email)).willReturn(Optional.ofNullable(user));

        assertThatExceptionOfType(EmailExistedException.class)
                .isThrownBy(() -> { userService.registerUser(email, name, password); });

//        verify(userRepository, never()).save(any());
    }

}