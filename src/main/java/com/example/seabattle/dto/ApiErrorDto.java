package com.example.seabattle.dto;

import com.example.seabattle.model.api.error.ApiError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class ApiErrorDto {
  private ApiError error;
}
