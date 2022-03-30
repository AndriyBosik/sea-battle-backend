package com.example.seabattle.controller;

import com.example.seabattle.model.UserStats;
import com.example.seabattle.model.User;
import com.example.seabattle.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{nickname}")
    public Optional<User> findByNickname(@PathVariable String nickname) {
        return userService.findByNickname(nickname);
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }

    @GetMapping("/{nickname}/stats")
    public Optional<UserStats> getUserStats(@PathVariable String nickname) {
        return userService.getUserWithStats(nickname);
    }
}
