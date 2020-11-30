package com.soten.eatgo.user.controller;

import com.soten.eatgo.user.domain.User;
import com.soten.eatgo.user.dto.UserLoginRequestDto;
import com.soten.eatgo.user.dto.UserLoginResponseDto;
import com.soten.eatgo.user.service.UserService;
import com.soten.eatgo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class SessionController {

    private UserService userService;

    private JwtUtil jwtUtil;

    public SessionController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/session")
    public ResponseEntity<UserLoginResponseDto> create(@RequestBody UserLoginRequestDto resource) throws URISyntaxException {
        String email = resource.getEmail();
        String password = resource.getPassword();

        User user = userService.authenticate(email, password);

        String accessToken = jwtUtil.createToken(user.getId(), user.getName());

        String url = "/session";
        return ResponseEntity.created(new URI(url))
                             .body(UserLoginResponseDto.builder()
                                     .accessToken(accessToken)
                                     .build());
    }

}
