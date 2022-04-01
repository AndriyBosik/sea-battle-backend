package com.example.seabattle.repository;

import com.example.seabattle.entity.UserEntity;
import com.example.seabattle.entity.projection.RatedUserProjection;
import com.example.seabattle.entity.projection.UserStatsProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByNicknameIgnoreCase(String nickname);

    UserStatsProjection findStatsByNickname(String nickname);

    Page<RatedUserProjection> findRatedUsersBy(Pageable pageable);
}
