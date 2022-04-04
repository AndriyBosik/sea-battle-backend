package com.example.seabattle.mapper;

import com.example.seabattle.dto.RatedUserDto;
import com.example.seabattle.dto.UserDto;
import com.example.seabattle.dto.UserStatsDto;
import com.example.seabattle.entity.UserEntity;
import com.example.seabattle.entity.projection.RatedUserProjection;
import com.example.seabattle.entity.projection.UserStatsProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
  UserDto toUser(UserEntity entity);

  UserEntity toUserEntity(UserDto model);

  @Mapping(target = "id", ignore = true)
  UserEntity toUnsavedEntity(UserDto model);

  UserStatsDto toUserStats(UserStatsProjection userStatsProjection);

  RatedUserDto toRatedUser(RatedUserProjection ratedUserProjection);
}
