package com.example.seabattle.service;

import com.example.seabattle.dto.*;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface UserService {
    Optional<UserDto> findByNickname(String nickname);

    void createUser(UserDto userDto);

    Optional<UserStatsDto> getUserWithStats(String nickname);

    PageDto<RatedUserDto> getRatedUsers(PageRequest pageRequest);

    void updateNickname(NicknameDto nicknameDto);
}
