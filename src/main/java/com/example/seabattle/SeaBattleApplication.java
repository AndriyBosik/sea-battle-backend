package com.example.seabattle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SeaBattleApplication {

  public static void main(String[] args) {
    SpringApplication.run(SeaBattleApplication.class, args);
  }

}
