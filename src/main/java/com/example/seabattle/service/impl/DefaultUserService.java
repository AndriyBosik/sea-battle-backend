package com.example.seabattle.service.impl;

import com.example.seabattle.dto.*;
import com.example.seabattle.entity.UserEntity;
import com.example.seabattle.entity.projection.RatedUserProjection;
import com.example.seabattle.mapper.UserMapper;
import com.example.seabattle.repository.UserRepository;
import com.example.seabattle.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
  public IdDto createUser(UserDto userDto) {
    UserEntity userEntity = userMapper.toUnsavedEntity(userDto);
    userRepository.save(userEntity);
    return new IdDto(
        userEntity.getId()
    );
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

  @Transactional
  @Override
  public void updateNickname(NicknameDto nicknameDto) {
    userRepository.updateUsername(nicknameDto.getOldNickname(), nicknameDto.getNewNickname());
  }
}
