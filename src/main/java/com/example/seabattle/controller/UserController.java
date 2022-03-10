package com.example.seabattle.controller;

import com.example.seabattle.model.User;
import com.example.seabattle.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
