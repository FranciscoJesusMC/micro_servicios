package com.game.service.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.game.service.dto.GameDTO;
import com.game.service.service.GameService;

@RestController
@RequestMapping("/api/game")
public class GameController {

	@Autowired
	private GameService gameService;
	
	@GetMapping
	public ResponseEntity<List<GameDTO>> getAllGames(){
		List<GameDTO> games = gameService.findAll();
		return ResponseEntity.ok(games);
	}
	
	@GetMapping("/{gameId}")
	public ResponseEntity<GameDTO> getGameById(@PathVariable(name = "gameId")String gameId){
		GameDTO game = gameService.findGameById(gameId);
		return ResponseEntity.ok(game);
	}
	
	@PostMapping
	public ResponseEntity<GameDTO> createGame (@Valid @RequestBody GameDTO gameDTO){
		GameDTO game = gameService.createGame(gameDTO);
		return new ResponseEntity<>(game,HttpStatus.CREATED);
	}
}
