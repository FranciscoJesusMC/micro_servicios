package com.game.service.service;

import java.util.List;

import com.game.service.dto.GameDTO;

public interface GameService {

	public List<GameDTO> findAll();
	
	public GameDTO findGameById(String gameId);
	
	public GameDTO createGame(GameDTO gameDTO);
}
