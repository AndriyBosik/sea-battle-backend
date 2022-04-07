package com.example.seabattle.mapper;

import com.auth0.json.auth.TokenHolder;
import com.example.seabattle.dto.TokenDto;
import org.mapstruct.Mapper;

@Mapper
public interface TokenMapper {
  TokenDto toDto(TokenHolder tokenHolder);
}
