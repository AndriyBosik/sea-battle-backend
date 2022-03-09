package com.example.seabattle.service;

import com.example.seabattle.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByNickname(String nickname);
}
