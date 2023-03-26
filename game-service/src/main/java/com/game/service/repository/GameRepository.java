package com.game.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.game.service.entity.Game;

public interface GameRepository extends JpaRepository<Game, String> {

}
