package com.soten.eatgo.user;

import com.soten.eatgo.user.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class SessionController {

    private UserService userService;

    public SessionController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/session")
    public ResponseEntity<?> create() throws URISyntaxException {

        String url = "/session";
        return ResponseEntity.created(new URI(url))
                             .body("{\"accessToken\":\"ACCESSTOKEN\"}");
    }

}
