package com.example.seabattle.service;

import com.example.seabattle.dto.*;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface UserService {
  Optional<UserDto> findByNickname(String nickname);

  IdDto createUser(UserDto userDto);

  Optional<UserStatsDto> getUserWithStats();

  PageDto<RatedUserDto> getRatedUsers(PageRequest pageRequest);

  void updateNickname(NicknameDto nicknameDto);

  Optional<IdDto> getUserId(String nickname);
}
