package com.example.seabattle.service.impl;

import com.example.seabattle.entity.UserEntity;
import com.example.seabattle.mapper.UserMapper;
import com.example.seabattle.model.RatedUser;
import com.example.seabattle.model.User;
import com.example.seabattle.model.UserStats;
import com.example.seabattle.repository.UserRepository;
import com.example.seabattle.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public Optional<User> findByNickname(String nickname) {
        UserEntity entity = userRepository.findByNicknameIgnoreCase(nickname);
        return Optional.ofNullable(userMapper.toUser(entity));
    }

    @Override
    public void createUser(User user) {
        userRepository.save(userMapper.toUnsavedEntity(user));
    }

    @Override
    public Optional<UserStats> getUserWithStats(String nickname) {
        return Optional.ofNullable(userMapper.toUserStats(userRepository.findStatsByNickname(nickname)));
    }

    @Override
    public List<RatedUser> getRatedUsers() {
        return userRepository.findRatedUsersByOrderByScoreDesc().stream()
                .map(userMapper::toRatedUser)
                .collect(Collectors.toList());
    }
}
