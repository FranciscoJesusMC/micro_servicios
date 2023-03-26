package com.game.service.serviceImpl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.game.service.dto.GameDTO;
import com.game.service.entity.Game;
import com.game.service.exceptions.GameAppException;
import com.game.service.exceptions.ResourceNotFoundException;
import com.game.service.repository.GameRepository;
import com.game.service.service.GameService;

@Service
public class GameServiceImpl implements GameService{

	@Autowired
	private GameRepository gameRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private Game mapToEntity(GameDTO gameDTO) {
		Game game = modelMapper.map(gameDTO, Game.class);
		return game;		
	}
	
	private GameDTO mapToDto(Game game) {
		GameDTO gameDTO = modelMapper.map(game, GameDTO.class);
		return gameDTO;
	}

	@Override
	public List<GameDTO> findAll() {
		List<Game> games = gameRepository.findAll();
		if(games.isEmpty()) {
			throw new GameAppException(HttpStatus.NOT_FOUND,"game list is empty");
		}
		return games.stream().map(game -> mapToDto(game)).collect(Collectors.toList());
	}

	@Override
	public GameDTO findGameById(String gameId) {
		Game game = gameRepository.findById(gameId).orElseThrow(()-> new ResourceNotFoundException("Game", "gameId", gameId));
		return mapToDto(game);
	}

	@Override
	public GameDTO createGame(GameDTO gameDTO) {
		Game game = mapToEntity(gameDTO);
		
		game.setGameId(UUID.randomUUID().toString());
		
		Game newGame = gameRepository.save(game);
		
		GameDTO saveGame = mapToDto(newGame);
		
		return saveGame;
	}

}
