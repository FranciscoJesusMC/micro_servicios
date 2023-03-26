package com.user.service.service;

import java.util.List;

import com.user.service.dto.UserDTO;
import com.user.service.entity.Game;

public interface UserService {

	public List<UserDTO> findAll();
	
	public UserDTO findUserById(String userId);
	
	public UserDTO createUser(UserDTO userDTO);
	
	public Game createGame(Game game);
}
