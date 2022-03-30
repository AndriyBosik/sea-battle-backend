package com.example.seabattle.service;

import com.example.seabattle.model.User;
import com.example.seabattle.model.UserStats;

import java.util.Optional;

public interface UserService {
    Optional<User> findByNickname(String nickname);

    void createUser(User user);

    Optional<UserStats> getUserWithStats(String nickname);
}
