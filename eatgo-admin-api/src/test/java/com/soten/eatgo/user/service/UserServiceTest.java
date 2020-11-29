package com.soten.eatgo.user.service;

import com.soten.eatgo.user.domain.User;
import com.soten.eatgo.user.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
    @DisplayName("유저 불러오기")
    void getUsers() {
        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(User.builder().email("maxosa@naver.com")
                .name("younho")
                .level(1L)
                .build());

        given(userRepository.findAll()).willReturn(mockUsers);

        List<User> users = userService.getUsers();

        User user = users.get(0);

        assertThat(user.getName()).isEqualTo("younho");
    }

    @Test
    @DisplayName("유저 추가")
    void addUser() {
        String email = "maxosa@naver.com";
        String name = "younho";

        User mockUser = User.builder().email(email)
                .name(name)
                .build();

        given(userRepository.save(any())).willReturn(mockUser);

        User user = userService.addUser(email, name);

        assertThat(user.getName()).isEqualTo(name);
    }

    @Test
    void updateUser() {
        Long id = 1004L;
        String email = "admin@naver.com";
        String name = "Customer";
        Long level = 100L;

        User mockUser = User.builder()
                .id(id)
                .email(email)
                .name("Administrator")
                .level(1L)
                .build();

        given(userRepository.findById(id)).willReturn(Optional.of(mockUser));

        User user = userService.updateUser(id, email, name, level);

        verify(userRepository).findById(eq(id));

        assertThat(user.getName()).isEqualTo("Customer");
        assertThat(user.isAdmin()).isTrue();
    }

    @Test
    void deactivateUser() {
        Long id = 1004L;

        User mockUser = User.builder()
                .id(id)
                .email("admin@naver.com")
                .name("Customer")
                .build();

        given(userRepository.findById(id)).willReturn(Optional.of(mockUser));

        User user = userService.deactivateUser(1004L);

        verify(userRepository).findById(1004L);

        assertThat(user.getName()).isEqualTo("Customer");
        assertThat(user.isAdmin()).isFalse();
        assertThat(user.isActive()).isFalse();
    }

}
