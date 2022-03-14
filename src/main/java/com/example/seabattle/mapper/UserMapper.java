package com.example.seabattle.mapper;

import com.example.seabattle.entity.UserEntity;
import com.example.seabattle.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toModel(UserEntity entity);

    UserEntity toEntity(User model);

    @Mapping(target = "id", ignore = true)
    UserEntity toUnsavedEntity(User model);
}
