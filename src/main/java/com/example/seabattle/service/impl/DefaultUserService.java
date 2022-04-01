package com.example.seabattle.service.impl;

import com.example.seabattle.dto.PageDto;
import com.example.seabattle.dto.RatedUserDto;
import com.example.seabattle.dto.UserDto;
import com.example.seabattle.dto.UserStatsDto;
import com.example.seabattle.entity.UserEntity;
import com.example.seabattle.entity.projection.RatedUserProjection;
import com.example.seabattle.mapper.UserMapper;
import com.example.seabattle.repository.UserRepository;
import com.example.seabattle.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultUserService implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public Optional<UserDto> findByNickname(String nickname) {
        UserEntity entity = userRepository.findByNicknameIgnoreCase(nickname);
        return Optional.ofNullable(userMapper.toUser(entity));
    }

    @Override
    public void createUser(UserDto userDto) {
        userRepository.save(userMapper.toUnsavedEntity(userDto));
    }

    @Override
    public Optional<UserStatsDto> getUserWithStats(String nickname) {
        return Optional.ofNullable(userMapper.toUserStats(userRepository.findStatsByNickname(nickname)));
    }

    @Override
    public PageDto<RatedUserDto> getRatedUsers(PageRequest pageRequest) {
        Page<RatedUserProjection> page = userRepository.findRatedUsersBy(pageRequest);
        return new PageDto<>(
            page.getTotalPages(),
            page.map(userMapper::toRatedUser).stream()
                    .collect(Collectors.toList())
        );
    }
}
