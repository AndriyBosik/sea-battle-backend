package com.example.seabattle.service;

import com.example.seabattle.dto.PageDto;
import com.example.seabattle.dto.RatedUserDto;
import com.example.seabattle.dto.UserDto;
import com.example.seabattle.dto.UserStatsDto;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface UserService {
    Optional<UserDto> findByNickname(String nickname);

    void createUser(UserDto userDto);

    Optional<UserStatsDto> getUserWithStats(String nickname);

    PageDto<RatedUserDto> getRatedUsers(PageRequest pageRequest);
}
