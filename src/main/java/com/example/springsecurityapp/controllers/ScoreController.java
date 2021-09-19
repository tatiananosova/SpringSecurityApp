package com.example.springsecurityapp.controllers;

import com.example.springsecurityapp.entities.User;
import com.example.springsecurityapp.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/score")
@Slf4j
@RequiredArgsConstructor
public class ScoreController {
    private final UserService userService;

    @GetMapping("/current")
    public String getCurrent(Principal principal) {
        User user = getUserFromPrincipal(principal);
        return user.getUsername() + " (" + user.getEmail() + ") score: " + user.getScore();
    }

    @GetMapping("/inc")
    public String incCurrent(Principal principal) {
        userService.incScore(getUserFromPrincipal(principal));
        return "Score increased";
    }

    @GetMapping("/dec")
    public String decCurrent(Principal principal) {
        userService.decScore(getUserFromPrincipal(principal));
        return "Score decreased";
    }

    @GetMapping("/{id}")
    public String getForUser(@RequestParam Long id) {
        return String.valueOf(userService.getScoreById(id));
    }

    private User getUserFromPrincipal(Principal principal) {
        return userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("unable to find user by username: " + principal.getName()));
    }
}
