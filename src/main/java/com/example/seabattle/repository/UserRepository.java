package com.example.seabattle.repository;

import com.example.seabattle.entity.UserEntity;
import com.example.seabattle.entity.projection.UserStatsProjection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByNicknameIgnoreCase(String nickname);

    UserStatsProjection findStatsByNickname(String nickname);
}
