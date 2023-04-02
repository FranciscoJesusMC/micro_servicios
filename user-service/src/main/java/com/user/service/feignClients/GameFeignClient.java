package com.user.service.feignClients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.user.service.entity.Game;

@FeignClient(name = "game-service",url = "http://localhost:8081")
public interface GameFeignClient {

	@PostMapping("/api/game")
	public Game save(@RequestBody Game game);
	
	@GetMapping("/api/game/{gameId}")
	public Game getGame(@PathVariable(name = "gameId")String gameId);
	
	@GetMapping("/api/game")
	public List<Game> getAllGames();
}
