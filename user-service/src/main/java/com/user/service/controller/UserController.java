package com.user.service.controller;

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

import com.user.service.dto.UserDTO;
import com.user.service.entity.Game;
import com.user.service.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> getAll(){
		List<UserDTO> users = userService.findAll();
		return ResponseEntity.ok(users);
	}
	
	@CircuitBreaker(name = "userCB",fallbackMethod = "fallBackGetUser")
	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable(name = "userId")String userId){
		UserDTO  user = userService.findUserById(userId);
		return ResponseEntity.ok(user);
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
		UserDTO user = userService.createUser(userDTO);
		return new ResponseEntity<>(user,HttpStatus.CREATED);
	}
	
	@CircuitBreaker(name = "gameCB",fallbackMethod = "fallBackCreateGame")
	@PostMapping("/game")
	public ResponseEntity<Game> saveGame(@RequestBody Game game){
		Game newGame = userService.createGame(game);
		return new ResponseEntity<>(newGame,HttpStatus.CREATED);
	}
	
	@GetMapping("/allGames")
	public ResponseEntity<List<Game>> getAllGames(){
		List<Game> games = userService.listAllGames();
		return ResponseEntity.ok(games);
	}
	
	//Metodos para nuestros fallbacks
	
	private ResponseEntity<UserDTO> fallBackGetUser(@PathVariable(name = "userId")String userId,RuntimeException exception){
		return new ResponseEntity("El usuario" + userId +" no esta disponible",HttpStatus.OK);
	}
	
	private ResponseEntity<Game> fallBackCreateGame(@RequestBody Game game,RuntimeException exception){
		return new ResponseEntity("El servicio de registrar juegos no esta disponible",HttpStatus.OK);
	}
	
}
