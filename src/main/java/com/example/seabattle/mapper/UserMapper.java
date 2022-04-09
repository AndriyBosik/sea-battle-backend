package com.example.seabattle.mapper;

import com.example.seabattle.dto.*;
import com.example.seabattle.entity.UserEntity;
import com.example.seabattle.entity.projection.RatedUserProjection;
import com.example.seabattle.entity.projection.UserContextProjection;
import com.example.seabattle.entity.projection.UserStatsProjection;
import com.example.seabattle.model.UserPrincipal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
  UserDto toUser(UserEntity entity);

  UserPrincipal toPrincipal(String email, UserContextProjection userContextProjection);

  @Mapping(target = "id", ignore = true)
  UserEntity toUnsavedEntity(UserDto model);

  UserStatsDto toUserStats(UserStatsProjection userStatsProjection);

  RatedUserDto toRatedUser(RatedUserProjection ratedUserProjection);
}
