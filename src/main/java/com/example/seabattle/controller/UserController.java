package com.example.seabattle.controller;

import com.example.seabattle.dto.*;
import com.example.seabattle.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{nickname}")
    public Optional<UserDto> findByNickname(@PathVariable String nickname) {
        return userService.findByNickname(nickname);
    }

    @PostMapping
    public void createUser(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
    }

    @GetMapping("/{nickname}/stats")
    public Optional<UserStatsDto> getUserStats(@PathVariable String nickname) {
        return userService.getUserWithStats(nickname);
    }

    @GetMapping("/rated")
    public PageDto<RatedUserDto> getRatedUsers(
            @RequestParam("pageNumber") int pageNumber,
            @RequestParam("size") int size
    ) {
        return userService.getRatedUsers(PageRequest.of(pageNumber, size, Sort.by("score").descending()));
    }

    @PatchMapping("/username")
    public ResponseEntity<String> updateNickname(@RequestBody NicknameDto nicknameDto) {
        userService.updateNickname(nicknameDto);
        return ResponseEntity.ok("Updated successfully");
    }
}
