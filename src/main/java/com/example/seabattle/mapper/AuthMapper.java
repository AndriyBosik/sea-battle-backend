package com.example.seabattle.mapper;

import com.example.seabattle.dto.PasswordlessRegisterDto;
import com.example.seabattle.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper
public interface AuthMapper {
  UserDto toUserDto(PasswordlessRegisterDto passwordlessRegisterDto);
}
