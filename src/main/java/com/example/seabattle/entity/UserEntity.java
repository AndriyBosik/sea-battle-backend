package com.example.seabattle.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nickname", nullable = false)
  private String nickname;

  @Column(name = "score")
  private int score;

  @Column(name = "coins")
  private int coins;
}
