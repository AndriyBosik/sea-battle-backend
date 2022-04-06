package com.example.seabattle.mapper;

import com.example.seabattle.dto.*;
import com.example.seabattle.entity.UserEntity;
import com.example.seabattle.entity.projection.RatedUserProjection;
import com.example.seabattle.entity.projection.UserStatsProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
  UserDto toUser(UserEntity entity);

  UserDto toUser(RegisterDto registerDto);

  UserDto toUser(PasswordlessRegisterDto nicknameDto);

  UserEntity toUserEntity(UserDto model);

  @Mapping(target = "id", ignore = true)
  UserEntity toUnsavedEntity(UserDto model);

  UserStatsDto toUserStats(UserStatsProjection userStatsProjection);

  RatedUserDto toRatedUser(RatedUserProjection ratedUserProjection);
}
