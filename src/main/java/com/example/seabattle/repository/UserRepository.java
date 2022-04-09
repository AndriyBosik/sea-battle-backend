package com.example.seabattle.repository;

import com.example.seabattle.entity.UserEntity;
import com.example.seabattle.entity.projection.RatedUserProjection;
import com.example.seabattle.entity.projection.UserContextProjection;
import com.example.seabattle.entity.projection.UserStatsProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
  UserEntity findByNicknameIgnoreCase(String nickname);

  UserStatsProjection findStatsById(Long id);

  Page<RatedUserProjection> findRatedUsersBy(Pageable pageable);

  @Modifying
  @Query("update UserEntity u set u.nickname = :newNickname where u.id = :id")
  void updateUsername(
      @Param(value = "id") Long id,
      @Param(value = "newNickname") String newNickname);

  @Query("select u.id from UserEntity u where u.nickname=:nickname")
  Long findUserIdByNickname(
      @Param(value = "nickname") String nickname);

  UserContextProjection findUserById(Long id);
}
