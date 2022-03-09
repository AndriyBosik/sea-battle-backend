package com.example.seabattle.mapper;

import com.example.seabattle.entity.UserEntity;
import com.example.seabattle.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toModel(UserEntity entity);
}
