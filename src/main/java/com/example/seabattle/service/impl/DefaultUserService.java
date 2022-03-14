package com.example.seabattle.service.impl;

import com.example.seabattle.entity.UserEntity;
import com.example.seabattle.mapper.UserMapper;
import com.example.seabattle.model.User;
import com.example.seabattle.repository.UserRepository;
import com.example.seabattle.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public Optional<User> findByNickname(String nickname) {
        UserEntity entity = userRepository.findByNicknameIgnoreCase(nickname);
        return entity == null ? Optional.empty() : Optional.of(userMapper.toModel(entity));
    }

    @Override
    public void createUser(User user) {
        userRepository.save(userMapper.toUnsavedEntity(user));
    }
}
