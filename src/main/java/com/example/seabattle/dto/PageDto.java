package com.example.seabattle.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PageDto<T> {
    private int totalPages;
    private List<T> items;
}
